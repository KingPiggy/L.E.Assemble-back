package com.hoondragonite.leassemble.web.dto;

import com.hoondragonite.leassemble.config.auth.dto.SessionUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private Long id;
    private String name;
    private String email;
    private String picture;

    @Builder
    public UserRequestDto(SessionUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
