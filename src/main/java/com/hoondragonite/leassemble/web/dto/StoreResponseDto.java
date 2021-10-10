package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.user.User;
import lombok.Getter;

@Getter
public class StoreResponseDto {
    private Long id;
    private String name;
    private String info;
    private String tel;
    private String status;
    private User ownerUser;

    public StoreResponseDto(Store entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.info = entity.getInfo();
        this.tel = entity.getTel();
        this.status = entity.getStatus();
        this.ownerUser = entity.getOwnerUser();
    }
}
