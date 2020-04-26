package com.devchris.admin.controller.api;

import com.devchris.admin.ifs.CrudInterface;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.OrderGroupApiRequest;
import com.devchris.admin.model.network.response.OrderGroupApiResponse;
import com.devchris.admin.service.OrderGroupApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupApiLogicService orderGroupApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderGroupApiResponse> create(@RequestBody Header<OrderGroupApiRequest> ApiRequest) {
        return orderGroupApiLogicService.create(ApiRequest);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderGroupApiResponse> read(@PathVariable Long id) {
        return orderGroupApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderGroupApiResponse> update(@RequestBody Header<OrderGroupApiRequest> ApiRequest) {
        return orderGroupApiLogicService.update(ApiRequest);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable  Long id) {
        return orderGroupApiLogicService.delete(id);
    }
}
