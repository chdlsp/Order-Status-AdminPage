package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import com.devchris.admin.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderDetailRepositoryTests extends AdminApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create() {

        LocalDateTime nowTime = LocalDateTime.now();

        OrderDetail orderDetail = OrderDetail.builder()
                .status("WAITING")
                .arrivalDate(nowTime.plusDays(2))
                .quantity(1)
                .totalPrice(BigDecimal.valueOf(900000))
//                .orderGroupId(1L)
//                .itemId(1L)
                .createdAt(nowTime)
                .createdBy("OrderDetailRepoTests")
                .build();

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);
    }

}