package com.hoondragonite.leassemble.domain.store;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import com.hoondragonite.leassemble.domain.user.User;
import com.hoondragonite.leassemble.web.dto.StoreUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Store extends BaseTimeEntity {
    @Id
    @Column(name = "STORE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "STORE_NM")
    private String name;

    @Column(length = 500, name = "STORE_INFO")
    private String info;

    @Column(name = "STORE_TEL")
    private String tel;

    @Column(name = "STORE_STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(nullable = false, name = "USER_ID")
    private User ownerUser;

    @Builder
    public Store(String name, String info, String tel, String status, User ownerUser) {
        this.name = name;
        this.info = info;
        this.tel = tel;
        this.status = status;
        this.ownerUser = ownerUser;
    }

    public void update(StoreUpdateRequestDto dto) {
        this.name = dto.getName();
        this.info = dto.getInfo();
        this.tel = dto.getTel();
        this.status = dto.getStatus();
    }
}