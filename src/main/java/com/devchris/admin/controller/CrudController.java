package com.devchris.admin.controller;

import com.devchris.admin.ifs.CrudInterface;
import com.devchris.admin.model.network.Header;
import org.springframework.web.bind.annotation.*;

public abstract class CrudController<Request, Response> implements CrudInterface<Request, Response> {

    protected CrudInterface<Request, Response> baseService; // 상속받은 클래스에서만 접근 가능

    @Override
    @PostMapping("")
    public Header<Response> create(@RequestBody Header<Request> ApiRequest) {
        return baseService.create(ApiRequest);
    }

    @Override
    @GetMapping("{id}")
    public Header<Response> read(@PathVariable Long id) {
        return baseService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<Response> update(@RequestBody Header<Request> ApiRequest) {
        return baseService.update(ApiRequest);
    }

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable Long id) {
        return baseService.delete(id);
    }
}
