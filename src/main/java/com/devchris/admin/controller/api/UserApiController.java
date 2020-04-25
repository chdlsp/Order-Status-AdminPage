package com.devchris.admin.controller.api;

import com.devchris.admin.ifs.CrudInterface;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.UserApiRequest;
import com.devchris.admin.model.network.response.UserApiResponse;
import com.devchris.admin.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // /api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> ApiRequest) {
        log.info("{}", ApiRequest);
        return userApiLogicService.create(ApiRequest);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read : {}", id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/user
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> ApiRequest) {
        log.info("{}", ApiRequest);
        return userApiLogicService.update(ApiRequest);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable  Long id) {
        log.info("read : {}", id);
        return userApiLogicService.delete(id);
    }
}
