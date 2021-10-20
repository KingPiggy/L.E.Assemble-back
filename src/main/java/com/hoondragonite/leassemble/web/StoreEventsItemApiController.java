package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.service.StoreEventsItemService;
import com.hoondragonite.leassemble.web.dto.StoreEventsItemResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/api/user/stores/{storeId}/store-events/{storeEventsId}")
@RestController
@RequiredArgsConstructor
public class StoreEventsItemApiController {
    private final StoreEventsItemService storeEventsItemService;

    @GetMapping("/store-events-items")
    public List<StoreEventsItemResponseDto> findAllStoreEventsItemByStoreEvents(@PathVariable Long storeEventsId){
        return storeEventsItemService.findAllStoreEventsItemByStoreEvents(storeEventsId);
    }

    @GetMapping("/store-events-items/{storeEventsItemId}")
    public StoreEventsItemResponseDto findStoreEventsItemById(@PathVariable Long storeEventsItemId){
        return storeEventsItemService.findById(storeEventsItemId);
    }
}
