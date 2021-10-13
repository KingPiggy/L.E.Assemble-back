package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.config.auth.LoginUser;
import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import com.hoondragonite.leassemble.service.StoreService;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreSaveRequestDto;
import com.hoondragonite.leassemble.web.dto.StoreUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/api/user")
@RestController
@RequiredArgsConstructor
public class StoreApiController {

    private final StoreService storeService;

    @GetMapping("/stores")
    List<StoreResponseDto> findAllStoreByUserId(@LoginUser SessionUser user){
        return storeService.findAllByUserId(user.getId());
    }

    @GetMapping("/stores/{id}")
    public StoreResponseDto findStoreByUserId(@PathVariable Long id){
        return storeService.findById(id);
    }

    @PostMapping("/stores")
    public Long saveStore(@RequestBody StoreSaveRequestDto dto){
        return storeService.save(dto);
    }

    @PutMapping("/stores/{id}")
    public Long updateStore(@PathVariable Long id, @RequestBody StoreUpdateRequestDto dto){
        return storeService.update(id, dto);
    }

    @DeleteMapping("/stores/{id}")
    public void deleteStore(@PathVariable Long id){
        storeService.delete(id);
    }

}
