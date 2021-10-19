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
    public List<StoreEventsResponseDto> findAllProductsByStoreId(@PathVariable Long storeId) {
        return storeEventsService.findAllStoreEventsByStoreId(storeId);
    }

    @GetMapping("/store-events/{storeEventsId}")
    public StoreEventsResponseDto findStoreEventsById(@PathVariable Long storeEventsId) {
        return storeEventsService.findById(storeEventsId);
    }

    @PostMapping("/store-events")
    public Long saveStoreEvents(@RequestBody StoreEventsSaveRequestDto dto, @PathVariable Long storeId) {
        return storeEventsService.saveStoreEvents(dto, storeId);
    }

    @PutMapping("/store-events/{storeEventsId}")
    public Long updateStoreEvents(@PathVariable Long storeEventsId, @RequestBody StoreEventsUpdateRequestDto dto) {
        return storeEventsService.updateStoreEvents(storeEventsId, dto);
    }

    @DeleteMapping("/store-events/{storeEventsId}")
    public void deleteStoreEvents(@PathVariable Long storeEventsId) {
        storeEventsService.deleteStoreEvents(storeEventsId);
    }
}
