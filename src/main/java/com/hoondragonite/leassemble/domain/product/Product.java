package com.hoondragonite.leassemble.domain.product;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;

import javax.persistence.*;

import com.hoondragonite.leassemble.domain.store.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity{
    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRODUCT_NM")
    private String name;

    @Column(name = "PRODUCT_PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;
}
