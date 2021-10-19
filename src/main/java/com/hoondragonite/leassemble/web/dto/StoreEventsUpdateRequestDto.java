package com.hoondragonite.leassemble.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreEventsUpdateRequestDto {
    private String name;
    private String info;
    private String startDate;
    private String endDate;

    @Builder
    public StoreEventsUpdateRequestDto(String name, String info, String startDate, String endDate) {
        this.name = name;
        this.info = info;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
