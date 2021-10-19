package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.service.StoreEventsService;
import com.hoondragonite.leassemble.web.dto.ProductResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/api/user/stores/{storeId}")
@RestController
@RequiredArgsConstructor
public class StoreEventsApiController {
    private final StoreEventsService storeEventsService;

    @GetMapping("/store-events")
    List<StoreEventsResponseDto> findAllProductsByStoreId(@PathVariable Long storeId) {
        return storeEventsService.findAllStoreEventsByStoreId(storeId);
    }

    @GetMapping("/store-events/{storeEventsId}")
    StoreEventsResponseDto findStoreEventsById(@PathVariable Long storeEventsId) {
        return storeEventsService.findById(storeEventsId);
    }
}
