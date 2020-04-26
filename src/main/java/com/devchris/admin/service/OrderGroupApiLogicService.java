package com.devchris.admin.service;

import com.devchris.admin.model.entity.OrderGroup;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.OrderGroupApiRequest;
import com.devchris.admin.model.network.response.OrderGroupApiResponse;
import com.devchris.admin.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> ApiRequest) {

        OrderGroupApiRequest body = ApiRequest.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(body.getOrderAt())
                .arrivalDate(body.getArrivalDate())
                .user(userRepository.getOne(body.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {

        Optional<OrderGroup> readOrderGroup = baseRepository.findById(id);
        log.info("readOrderGroup : {}", readOrderGroup);

        return readOrderGroup
                .map(orderGroup -> response(orderGroup))
                .orElseGet(()-> Header.ERROR("Data Not Exists"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> ApiRequest) {

        OrderGroupApiRequest body = ApiRequest.getData();

        return baseRepository.findById(body.getId())
                .map(orderGroup -> {

                    orderGroup.setStatus(body.getStatus())
                        .setOrderType(body.getOrderType())
                        .setRevAddress(body.getRevAddress())
                        .setRevName(body.getRevName())
                        .setPaymentType(body.getPaymentType())
                        .setTotalPrice(body.getTotalPrice())
                        .setTotalQuantity(body.getTotalQuantity())
                        .setOrderAt(body.getOrderAt())
                        .setArrivalDate(body.getArrivalDate())
                        .setUser(userRepository.getOne(body.getUserId()));
                    return orderGroup;
                })
                .map(newOrderGroup-> baseRepository.save(newOrderGroup))
                .map(returnOrderGroup-> response(returnOrderGroup))
                .orElseGet(()->Header.ERROR("Data Not Exists"));
    }

    @Override
    public Header delete(Long id) {

        Optional<OrderGroup> readOrderGroup = baseRepository.findById(id);

        return readOrderGroup.map(item-> {
            baseRepository.delete(item);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("Data Not Exists"));

    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {

        OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();

        return Header.OK(body);
    }
}
