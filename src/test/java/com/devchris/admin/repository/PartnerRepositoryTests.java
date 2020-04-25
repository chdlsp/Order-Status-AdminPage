package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import com.devchris.admin.model.entity.Partner;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PartnerRepositoryTests extends AdminApplicationTests {


    @Autowired
    private PartnerRepository partnerRepository;

    @Test
    public void create() {

        LocalDateTime nowTime = LocalDateTime.now();

        Partner partner = Partner.builder()
                .name("Partner01")
                .status("REGISTERED")
                .address("서울시 강남구")
                .callCenter("070-1111-1111")
                .partnerNumber("010-1111-1111")
                .businessNumber("1234567890123")
                .ceoName("홍길동")
                .registeredAt(nowTime)
                .createdAt(nowTime)
                .createdBy("PartnerRepoTests")
//                .categoryId(1L)
                .build();

        System.out.println("partner : " + partner.toString());

        Partner newPartner = partnerRepository.save(partner);
        Assert.assertNotNull(newPartner);


    }

}