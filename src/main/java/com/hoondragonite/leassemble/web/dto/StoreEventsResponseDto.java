package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.store.Store;
import lombok.Getter;

@Getter
public class StoreEventsResponseDto {
    private Long id;
    private String name;
    private String info;
    private String startDate;
    private String endDate;
    private Store store;

    public StoreEventsResponseDto(StoreEvents entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.info = entity.getInfo();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.store = entity.getStore();
    }
}
