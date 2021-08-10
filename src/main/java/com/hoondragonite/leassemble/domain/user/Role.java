package com.hoondragonite.leassemble.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "사용자"),
    OWNER("ROLL_OWNER", "오너");
    private final String key;
    private final String title;
}

