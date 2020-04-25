package com.devchris.admin.controller;

import com.devchris.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class PostController {

    // HTML <Form>
    // ajax 검색 등에 사용
    // http post body -> data
    //  json, xml, multipart-form, text-plain

    @PostMapping("/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }

}
