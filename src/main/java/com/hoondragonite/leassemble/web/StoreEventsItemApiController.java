package com.hoondragonite.leassemble.web;

import com.hoondragonite.leassemble.service.StoreEventsItemService;
import com.hoondragonite.leassemble.web.dto.StoreEventsItemResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsItemSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/store-events-items")
    public void saveAllStoreEventsItem(@RequestBody List<StoreEventsItemSaveRequestDto> dtoList, @PathVariable Long storeEventsId){
        storeEventsItemService.saveStoreEventsItem(dtoList, storeEventsId);
    }
}
