package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.events.StoreEvents;
import com.hoondragonite.leassemble.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreEventsSaveRequestDto {
    private String name;
    private String info;
    private String startDate;
    private String endDate;
    private Store store;

    @Builder
    public StoreEventsSaveRequestDto(String name, String info, String startDate, String endDate) {
        this.name = name;
        this.info = info;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public StoreEvents toEntity(){
        return StoreEvents.builder()
                .name(name)
                .info(info)
                .startDate(startDate)
                .endDate(endDate)
                .store(store)
                .build();
    }
}
