package com.devchris.admin.controller.api;

import com.devchris.admin.ifs.CrudInterface;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.ItemApiRequest;
import com.devchris.admin.model.network.response.ItemApiResponse;
import com.devchris.admin.service.ItemApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/item")
public class ItemApiController implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    @Override
    @PostMapping("")
    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> ApiRequest) {
        log.info("Item create : {}", ApiRequest);
        return itemApiLogicService.create(ApiRequest);
    }

    @Override
    @GetMapping("{id}")
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        log.info("Item read : {}", id);
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> ApiRequest) {
        log.info("Item update : {}", ApiRequest);
        return itemApiLogicService.update(ApiRequest);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        log.info("Item delete : {}", id);
        return itemApiLogicService.delete(id);
    }
}
