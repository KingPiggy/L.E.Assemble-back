package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreUpdateRequestDto {
    private String name;
    private String info;
    private String tel;
    private String status;

    @Builder
    public StoreUpdateRequestDto(String name, String info, String tel, String status) {
        this.name = name;
        this.info = info;
        this.tel = tel;
        this.status = status;
    }
}
