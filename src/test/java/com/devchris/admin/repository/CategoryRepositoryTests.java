package com.devchris.admin.repository;

import com.devchris.admin.AdminApplicationTests;
import com.devchris.admin.model.entity.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTests extends AdminApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "COMPUTER";
        String title = "컴퓨터";

        Category category = Category.builder()
                .type(type)
                .title(title)
                .build();

        System.out.println("category : " + category.toString());

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);

    }

    @Test
    public void read() {
        String type = "COMPUTER";
        Optional<Category> categoryOptional = categoryRepository.findByType(type);

        // select * from category where type = 'COMPUTER'
        categoryOptional.ifPresent(c -> {
            Assert.assertEquals(c.getType(), type);
        });
    }

}