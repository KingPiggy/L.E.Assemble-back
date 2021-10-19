package com.hoondragonite.leassemble.service;

import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.events.StoreEventsRepository;
import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.store.StoreRepository;
import com.hoondragonite.leassemble.web.dto.ProductResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsResponseDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsSaveRequestDto;
import com.hoondragonite.leassemble.web.dto.StoreEventsUpdateRequestDto;
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
    public StoreEventsResponseDto findById(Long storeEventsId) {
        StoreEvents storeEvents = storeEventsRepository.findById(storeEventsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트는 없습니다. id = " + storeEventsId));
        return new StoreEventsResponseDto(storeEvents);
    }

    @Transactional
    public Long saveStoreEvents(StoreEventsSaveRequestDto dto, Long storeId) {
        // DTO의 상점 정보 업데이트 후 저장
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상점은 없습니다. id = " + storeId));
        dto.setStore(store);

        return storeEventsRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    public Long updateStoreEvents(Long id, StoreEventsUpdateRequestDto dto){
        StoreEvents storeEvents = storeEventsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트는 없습니다. id = " + id));
        storeEvents.update(dto);
        return storeEvents.getId();
    }

    @Transactional
    public void deleteStoreEvents(Long storeEventsId){
        StoreEvents storeEvents = storeEventsRepository.findById(storeEventsId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이벤트는 없습니다. id = " + storeEventsId));
        storeEventsRepository.delete(storeEvents);
    }
}
