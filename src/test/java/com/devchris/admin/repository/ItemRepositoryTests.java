package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import com.devchris.admin.model.entity.Item;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/*
    <Item Create>

    {
      "transaction_time":"2020-04-25T22:57:56.692",
      "result_code":"OK",
      "description":"OK",
      "data":{
        "status":"REGISTERED",
        "name":"Notebook S20",
        "title":"Notebook",
        "content":"2020 SS Notebook",
        "price":900000,
        "brand_name":"Samsung",
        "registered_at":"2020-04-25T22:57:56.692",
        "unregistered_at":null,
        "partner_id":"2"
      }
    }

* */
class ItemRepositoryTests extends AdminApplicationTests {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {

/*        LocalDateTime nowTime = LocalDateTime.now();

        Item item = Item.builder()
                .name("Notebook")
                .status(ItemStatus.REGISTERED)
                .name("Samsung Computer")
                .title("Samsung Computer A100")
                .content("2019's New Model")
                .price(BigDecimal.valueOf(900000L))
                .brandName("Samsung")
                .registeredAt(nowTime)
                .createdAt(nowTime)
                .createdBy("ItemRepositoryTests")
//                .partnerId(1L)
                .build();

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);*/

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