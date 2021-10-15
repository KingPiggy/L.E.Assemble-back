package com.hoondragonite.leassemble.domain.product;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;

import javax.persistence.*;

import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.web.dto.ProductUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {
    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NM")
    private String name;

    @Column(name = "PRODUCT_INFO")
    private String info;

    @Column(name = "PRODUCT_PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @Builder
    public Product(String name, String info, double price, Store store) {
        this.name = name;
        this.info = info;
        this.price = price;
        this.store = store;
    }

    public void update(ProductUpdateRequestDto dto) {
        this.name = dto.getName();
        this.info = dto.getInfo();
        this.price = dto.getPrice();
    }
}
