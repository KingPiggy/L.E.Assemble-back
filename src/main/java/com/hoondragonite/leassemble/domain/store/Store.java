package com.hoondragonite.leassemble.domain.store;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import com.hoondragonite.leassemble.domain.user.User;
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

    @Column(length = 500, nullable = false, name = "STORE_NM")
    private String name;

    @Column(length = 1000, name = "STORE_INFO")
    private String info;

    @Column(name = "STORE_TEL")
    private String tel;

    @Column(name = "STORE_STATUS")
    private String status;

    @ManyToOne
    @JoinColumn(nullable = false, name = "USER_ID")
    private User ownerUser;
}