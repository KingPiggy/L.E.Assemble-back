package com.hoondragonite.leassemble.domain.events;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.web.dto.StoreEventsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StoreEvents extends BaseTimeEntity {
    @Id
    @Column(name = "STORE_EVENTS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STORE_EVENTS_NM")
    private String name;

    @Column(name = "STORE_EVENTS_INFO")
    private String info;

    @Column(name = "STORE_EVENTS_START_DATE")
    private String startDate;

    @Column(name = "STORE_EVENTS_END_DATE")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @Builder
    public StoreEvents(String name, String info, String startDate, String endDate, Store store) {
        this.name = name;
        this.info = info;
        this.startDate = startDate;
        this.endDate = endDate;
        this.store = store;
    }

    public void update(StoreEventsUpdateRequestDto dto){
        name = dto.getName();
        info = dto.getInfo();
        startDate = dto.getStartDate();
        endDate = dto.getEndDate();
    }
}
