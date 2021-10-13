package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.domain.store.Store;
import com.hoondragonite.leassemble.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreSaveRequestDto {
    private String name;
    private String info;
    private String tel;
    private String status;
    private User ownerUser;

    @Builder
    public StoreSaveRequestDto(String name, String info, String tel, String status, User ownerUser) {
        this.name = name;
        this.info = info;
        this.tel = tel;
        this.status = status;
        this.ownerUser = ownerUser;
    }

    public void setOwnerUser(User ownerUser){
        this.ownerUser = ownerUser;
    }

    public Store toEntity() {
        return Store.builder()
                .name(name)
                .info(info)
                .tel(tel)
                .status(status)
                .ownerUser(ownerUser)
                .build();
    }
}
