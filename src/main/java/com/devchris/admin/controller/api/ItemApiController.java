package com.devchris.admin.controller.api;

import com.devchris.admin.controller.CrudController;
import com.devchris.admin.model.network.request.ItemApiRequest;
import com.devchris.admin.model.network.response.ItemApiResponse;
import com.devchris.admin.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @PostConstruct // Controller 생성 전 서비스 매핑
    public void init() {
        this.baseService = itemApiLogicService;
    }

}
