package com.hoondragonite.leassemble.domain.store;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import com.hoondragonite.leassemble.domain.product.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StoreEventItem extends BaseTimeEntity {
    @Id
    @Column(name = "STORE_EVENT_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STORE_EVENT_ITEM_NM")
    private String name;

    @Column(name = "STORE_EVENT_ITEM_QTY")
    private int qty;

    @Column(name = "STORE_EVENT_ITEM_PRICE")
    private double price;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "STORE_EVENT_ID")
    private StoreEvent storeEvent;
}
