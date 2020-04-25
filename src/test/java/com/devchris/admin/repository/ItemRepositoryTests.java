package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import com.devchris.admin.model.entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

class ItemRepositoryTests extends AdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void createe() {

        LocalDateTime nowTime = LocalDateTime.now();

        Item item = Item.builder()
                .name("Notebook")
                .status("UNREGISTERED")
                .name("Samsung Computer")
                .title("Samsung Computer A100")
                .content("2019's New Model")
                .price(900000)
                .brandName("Samsung")
                .registeredAt(nowTime)
                .createdAt(nowTime)
                .createdBy("ItemRepositoryTests")
//                .partnerId(1L)
                .build();

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);

    }

    @Test
    public void read() {

        Optional<Item> newItem = itemRepository.findById(1L);
        Assert.assertTrue(newItem.isPresent());

        newItem.ifPresent(item -> {
            System.out.println(" item : " + item);
        });

    }

}