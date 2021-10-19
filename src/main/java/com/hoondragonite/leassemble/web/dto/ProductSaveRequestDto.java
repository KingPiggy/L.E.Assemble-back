package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.product.Product;
import com.hoondragonite.leassemble.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductSaveRequestDto {
    private String name;
    private String info;
    private double price;
    private Store store;

    @Builder
    public ProductSaveRequestDto(String name, String info, double price, Store store) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.store = store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .info(info)
                .price(price)
                .store(store)
                .build();
    }
}
