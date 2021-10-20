package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.events.StoreEventsItem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreEventsItemSaveRequestDto {
    private String name;
    private String info;
    private int qty;
    private double price;
    private StoreEvents storeEvents;

    @Builder
    public StoreEventsItemSaveRequestDto(String name, String info, int qty, double price, StoreEvents storeEvents) {
        this.name = name;
        this.info = info;
        this.qty = qty;
        this.price = price;
        this.storeEvents = storeEvents;
    }

    public void setStoreEvents(StoreEvents storeEvents) {
        this.storeEvents = storeEvents;
    }

    public StoreEventsItem toEntity() {
        return StoreEventsItem.builder()
                .name(name)
                .info(info)
                .qty(qty)
                .price(price)
                .storeEvents(storeEvents)
                .build();
    }

}
