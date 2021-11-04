package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.events.StoreEventsItem;
import lombok.Getter;

@Getter
public class StoreEventsItemResponseDto {
    private Long id;
    private String name;
    private String info;
    private int qty;
    private double price;
    private StoreEvents storeEvents;

    public StoreEventsItemResponseDto(StoreEventsItem entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.info = entity.getInfo();
        this.qty = entity.getQty();
        this.price = entity.getPrice();
        this.storeEvents = entity.getStoreEvents();
    }
}
