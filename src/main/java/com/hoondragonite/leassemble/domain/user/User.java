package com.hoondragonite.leassemble.domain.user;

import com.hoondragonite.leassemble.domain.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.security.AuthProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.*;

import com.hoondragonite.leassemble.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_NM")
    private String name;

    @Column(nullable = false, name = "USER_EMAIL")
    private String email;

    @Column(name = "USER_PICTURE")
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "USER_ROLE")
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}