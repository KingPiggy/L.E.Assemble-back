package com.hoondragonite.leassemble.service;

import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.events.StoreEventsRepository;
import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.web.dto.ProductResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreEventsService {

    private final StoreEventsRepository storeEventsRepository;

    private final StoreRepository storeRepository;

    //코드 개선 필요
    @Transactional(readOnly = true)
    public List<StoreEventsResponseDto> findAllStoreEventsByStoreId(Long storeId) {
        List<StoreEvents> storeEvents = storeEventsRepository.findByStore_Id(storeId);
        List<StoreEventsResponseDto> responseDtos = new ArrayList<>();

        for (StoreEvents s : storeEvents) {
            responseDtos.add(new StoreEventsResponseDto(s));
        }

        return responseDtos;
    }

    @Transactional(readOnly = true)
    public StoreEventsResponseDto findById(Long storeEventsId){
        StoreEvents storeEvents = storeEventsRepository.findById(storeEventsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트는 없습니다. id = " + storeEventsId));
        return new StoreEventsResponseDto(storeEvents);
    }
}
