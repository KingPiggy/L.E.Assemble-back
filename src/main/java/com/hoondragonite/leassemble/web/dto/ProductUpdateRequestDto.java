package com.hoondragonite.leassemble.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductUpdateRequestDto {
    private String name;
    private String info;
    private double price;

    @Builder
    public ProductUpdateRequestDto(String name, String info, double price) {
        this.name = name;
        this.info = info;
        this.price = price;
    }
}
