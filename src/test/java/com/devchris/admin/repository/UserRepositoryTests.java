package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import com.devchris.admin.model.entity.User;
import com.devchris.admin.model.enumClass.UserStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


@Slf4j
public class UserRepositoryTests extends AdminApplicationTests {

    @Autowired // Dependency Injection (DI)
    private UserRepository userRepository;

    @Test
    public void create() {

        LocalDateTime nowTime = LocalDateTime.now();
        User user = User.builder()
                .account("TestUser02")
                .password("TestUser02")
                .status(UserStatus.REGISTERED)
                .email("TestUser02@gmail.com")
                .phoneNumber("010-1111-1111")
                .registeredAt(nowTime)
                .build();

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);

    }

    @Test
    @Transactional
    public void read() {

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");

        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("Name          : " + orderGroup.getRevName());
            System.out.println("RevAddress    : " + orderGroup.getRevAddress());
            System.out.println("TotalPrice    : " + orderGroup.getTotalPrice());
            System.out.println("TotalQuantity : " + orderGroup.getTotalQuantity());

            orderGroup.getOrderDetailList().forEach(orderDetail -> {
                System.out.println("Status      : " + orderDetail.getStatus());
                System.out.println("ArrivalDate : " + orderDetail.getArrivalDate());

                System.out.println("PartnerName : " + orderDetail.getItem().getPartner().getName());
                System.out.println("CategoryName : " + orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("ItemName : " + orderDetail.getItem().getName());

            });

        });

        Assert.assertNotNull(user);
    }

    @Test
    public void update() {

        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser -> {
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("UserRepositoryTestsUpdateTest");

            userRepository.save(selectUser);
            System.out.println(" user : " + selectUser);

        });

    }

    @Test
    @Transactional // delete 후 Rollback 처리
    public void delete() {
        Optional<User> user = userRepository.findById(1L);
        Assert.assertTrue(user.isPresent());

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(1L);
        Assert.assertFalse(deleteUser.isPresent());

    }

}