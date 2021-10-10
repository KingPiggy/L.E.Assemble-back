package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreApiController {
    private final StoreService storeService;

}
