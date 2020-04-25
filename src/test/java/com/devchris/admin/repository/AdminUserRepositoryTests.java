package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import com.devchris.admin.model.entity.AdminUser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AdminUserRepositoryTests extends AdminApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create() {

        LocalDateTime nowTime = LocalDateTime.now();

        AdminUser adminUser = AdminUser.builder()
                .account("AdminUser03")
                .password("AdminUser03")
                .status("REGISTERED")
                .role("PARTNER")
                .build();

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);

        newAdminUser.setAccount("CHANGE");
        adminUserRepository.save(newAdminUser);

    }

}