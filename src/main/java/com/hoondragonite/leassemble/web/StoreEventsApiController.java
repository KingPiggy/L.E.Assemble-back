package com.hoondragonite.leassemble.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/api/user/stores/{storeId}")
@RestController
@RequiredArgsConstructor
public class StoreEventsApiController {

}
