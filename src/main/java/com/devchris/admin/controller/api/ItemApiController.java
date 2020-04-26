package com.devchris.admin.controller.api;

import com.devchris.admin.controller.CrudController;
import com.devchris.admin.model.entity.Item;
import com.devchris.admin.model.network.request.ItemApiRequest;
import com.devchris.admin.model.network.response.ItemApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}
