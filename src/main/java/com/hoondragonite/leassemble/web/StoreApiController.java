package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.service.StoreService;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class StoreApiController {
    private final StoreService storeService;

    @GetMapping("api/store/{id}")
    public StoreResponseDto findById(@PathVariable Long id){
        return storeService.findById(id);
    }
}
