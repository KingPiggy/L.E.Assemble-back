package com.hoondragonite.leassemble.domain.events;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StoreEventsItem extends BaseTimeEntity {
    @Id
    @Column(name = "STORE_EVENTS_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "STORE_EVENTS_ITEM_NM")
    private String name;

    @Column(name= "STORE_EVENTS_ITEM_INFO")
    private String info;

    @Column(name= "STORE_EVENTS_ITEM_QTY")
    private int qty;

    @Column(name= "STORE_EVENTS_ITEM_PRICE")
    private double price;

    @ManyToOne
    @JoinColumn(name = "STORE_EVENTS_ID")
    private StoreEvents storeEvents;

    @Builder
    public StoreEventsItem(String name, String info, int qty, double price, StoreEvents storeEvents) {
        this.name = name;
        this.info = info;
        this.qty = qty;
        this.price = price;
        this.storeEvents = storeEvents;
    }

}
