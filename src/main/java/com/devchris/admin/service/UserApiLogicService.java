package com.devchris.admin.service;

import com.devchris.admin.ifs.CrudInterface;
import com.devchris.admin.model.entity.User;
import com.devchris.admin.model.enumClass.UserStatus;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.UserApiRequest;
import com.devchris.admin.model.network.response.UserApiResponse;
import com.devchris.admin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

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

        User newUser = userRepository.save(user);

        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository.findById(id)
                .map(user -> response(user)) // map 을 통해 다른 type 으로 변환
                .orElseGet(()->Header.ERROR("Data Not Exists"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> ApiRequest) {

        UserApiRequest body = ApiRequest.getData();
        Optional<User> readUser = userRepository.findById(body.getId());

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
            .map(user -> userRepository.save(user))     // update
            .map(updateUser -> response(updateUser))    // create userApiResponse
            .orElseGet(()->Header.ERROR("Data Not Exist"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> readUser = userRepository.findById(id);

        return readUser.map(user->{
            userRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(()->Header.ERROR("Data Not Exist"));
    }

    private Header<UserApiResponse> response(User user) {

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
        return Header.OK(userApiResponse);
    }
}
