package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.service.StoreEventsService;
import com.hoondragonite.leassemble.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/store-events")
    Long saveStoreEvents(@RequestBody StoreEventsSaveRequestDto dto, @PathVariable Long storeId) {
        return storeEventsService.save(dto, storeId);
    }

}
