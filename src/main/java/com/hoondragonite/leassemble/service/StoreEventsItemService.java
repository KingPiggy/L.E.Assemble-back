package com.hoondragonite.leassemble.service;

import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.events.StoreEventsItem;
import com.hoondragonite.leassemble.domain.events.StoreEventsItemRepository;
import com.hoondragonite.leassemble.domain.events.StoreEventsRepository;
import com.hoondragonite.leassemble.web.dto.StoreEventsItemResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreEventsItemService {
    private final StoreEventsItemRepository storeEventsItemRepository;
    private final StoreEventsRepository storeEventsRepository;

    //코드 개선 필요
    @Transactional(readOnly = true)
    public List<StoreEventsItemResponseDto> findAllStoreEventsItemByStoreEvents(Long storeEventsId) {
        List<StoreEventsItem> storeEventsItems = storeEventsItemRepository.findByStoreEvents_Id(storeEventsId);
        List<StoreEventsItemResponseDto> responseDtos = new ArrayList<>();

        for (StoreEventsItem s : storeEventsItems) {
            responseDtos.add(new StoreEventsItemResponseDto(s));
        }

        return responseDtos;
    }

    @Transactional(readOnly = true)
    public StoreEventsItemResponseDto findById(Long storeEventsItemId){
        StoreEventsItem storeEventsItem = storeEventsItemRepository.findById(storeEventsItemId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트 상품은 없습니다. id = " + storeEventsItemId));
        return new StoreEventsItemResponseDto(storeEventsItem);
    }
}
