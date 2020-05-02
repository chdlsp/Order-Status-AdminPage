package com.devchris.admin.service;

import com.devchris.admin.model.entity.OrderGroup;
import com.devchris.admin.model.entity.User;
import com.devchris.admin.model.enumClass.UserStatus;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.UserApiRequest;
import com.devchris.admin.model.network.response.ItemApiResponse;
import com.devchris.admin.model.network.response.OrderGroupApiResponse;
import com.devchris.admin.model.network.response.UserApiResponse;
import com.devchris.admin.model.network.response.UserOrderInfoApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> ApiRequest) {

        UserApiRequest body = ApiRequest.getData();

        User user = User.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(body.getPhoneNumber())
                .email(body.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(user -> response(user)) // map 을 통해 다른 type 으로 변환
                .map(userApiResponse -> Header.OK(userApiResponse))
                .orElseGet(()->Header.ERROR("Data Not Exists"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> ApiRequest) {

        UserApiRequest body = ApiRequest.getData();
        Optional<User> readUser = baseRepository.findById(body.getId());

        return readUser.map(user -> {
            user.setAccount(body.getAccount())
                    .setPassword(body.getPassword())
                    .setStatus(body.getStatus())
                    .setPhoneNumber(body.getPhoneNumber())
                    .setEmail(body.getEmail())
                    .setRegisteredAt(body.getRegisteredAt())
                    .setUnregisteredAt(body.getUnregisteredAt());
            return user;                                // new object return
        })
            .map(user -> baseRepository.save(user))     // update
            .map(updateUser -> response(updateUser))    // create userApiResponse
            .map(userApiResponse -> Header.OK(userApiResponse))
            .orElseGet(()->Header.ERROR("Data Not Exist"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> readUser = baseRepository.findById(id);

        return readUser.map(user->{
            baseRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(()->Header.ERROR("Data Not Exist"));
    }

    private UserApiResponse response(User user) {

        // user object -> userApiResponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // TODO: encryption
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + Data
        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);
        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user))
                .collect(Collectors.toList());

        return Header.OK(userApiResponseList);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        // user
        User user = baseRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        // orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponsesList = orderGroupList.stream()
                .map(orderGroup -> {
                    OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();

                    // item
                    List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                            .map(detail -> detail.getItem())
                            .map(item -> itemApiLogicService.response(item).getData())
                            .collect(Collectors.toList());

                    orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                    return orderGroupApiResponse;
                })

                .collect(Collectors.toList());
        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponsesList);
        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }
}
