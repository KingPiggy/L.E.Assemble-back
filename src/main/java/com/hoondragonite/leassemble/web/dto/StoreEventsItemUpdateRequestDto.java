package com.hoondragonite.leassemble.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreEventsItemUpdateRequestDto {
    private String name;
    private String info;
    private int qty;
    private double price;

    @Builder
    public StoreEventsItemUpdateRequestDto(String name, String info, int qty, double price) {
        this.name = name;
        this.info = info;
        this.qty = qty;
        this.price = price;
    }
}
