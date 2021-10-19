package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.store.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private String info;
    private double price;
    private Store store;

    public ProductResponseDto(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.info = entity.getInfo();
        this.price = entity.getPrice();
        this.store = entity.getStore();
    }
}
