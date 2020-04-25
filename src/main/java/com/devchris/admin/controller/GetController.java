package com.devchris.admin.controller;

import com.devchris.admin.model.SearchParam;
import com.devchris.admin.model.network.Header;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest() {
        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        String password = "bbbb";
        return id + " " + password;
    }

    @GetMapping("/getMultiParameter") // json 형태의 리턴 => 반환을 Model로 하면 json으로 변환됨
    public SearchParam getMultiParameter(SearchParam searchParam) {
        return searchParam;
    }

    @GetMapping("/header")
    public Header getHeader() {
        //{"transactionTime": null, "resultCode": "OK", "description": "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }
}
