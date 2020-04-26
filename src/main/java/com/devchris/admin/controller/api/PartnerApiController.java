package com.devchris.admin.controller.api;

import com.devchris.admin.controller.CrudController;
import com.devchris.admin.model.entity.Partner;
import com.devchris.admin.model.network.request.PartnerApiRequest;
import com.devchris.admin.model.network.response.PartnerApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {

}
