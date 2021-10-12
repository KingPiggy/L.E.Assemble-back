package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.config.auth.LoginUser;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import com.hoondragonite.leassemble.service.StoreService;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/user")
@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;

    @GetMapping("/test")
    void test(@LoginUser SessionUser user){
        System.out.println("USER  ID : " + user.getId());
    }

    @GetMapping("/stores")
    List<StoreResponseDto> findAllStoreByUserId(@LoginUser SessionUser user){
        return storeService.findAllByUserId(user.getId());
    }

    @GetMapping("/stores/{id}")
    public StoreResponseDto findStoreByUserId(@PathVariable Long id){
        return storeService.findById(id);
    }
}

