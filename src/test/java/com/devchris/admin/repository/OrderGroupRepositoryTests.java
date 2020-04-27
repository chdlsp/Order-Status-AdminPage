package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;

class OrderGroupRepositoryTests extends AdminApplicationTests {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

/*    @Test
    public void create() {

        LocalDateTime nowTime = LocalDateTime.now();

        OrderGroup orderGroup = OrderGroup.builder()
                .status("COMPLETE")
                .orderType(OrderType.ALL)
                .revAddress("서울시 강남구")
                .revName("홍길동")
                .paymentType("CARD")
                .totalPrice(BigDecimal.valueOf(900000))
                .totalQuantity(1)
                .orderAt(nowTime.minusDays(2))
                .arrivalDate(nowTime)
                .createdAt(nowTime)
                .createdBy("OrderGroupRepoTests")
//                 .userId(1L)
                .build();

        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        Assert.assertNotNull(newOrderGroup);

    }*/
}