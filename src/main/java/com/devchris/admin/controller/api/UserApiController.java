package com.devchris.admin.controller.api;

import com.devchris.admin.controller.CrudController;
import com.devchris.admin.model.entity.User;
import com.devchris.admin.model.network.Header;
import com.devchris.admin.model.network.request.UserApiRequest;
import com.devchris.admin.model.network.response.UserApiResponse;
import com.devchris.admin.model.network.response.UserOrderInfoApiResponse;
import com.devchris.admin.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @GetMapping("")
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15) Pageable pageable) {
        log.info("pageable : {}", pageable);
        return userApiLogicService.search(pageable);
    }

    @GetMapping("{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
        return userApiLogicService.orderInfo(id);
    }

}
