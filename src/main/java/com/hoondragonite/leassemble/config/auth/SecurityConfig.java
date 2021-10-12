package com.hoondragonite.leassemble.config.auth;

import com.hoondragonite.leassemble.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/assets/**", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/profile", "/api/profile").permitAll() // 배포 시 서버에서 사용하는 URL
                .antMatchers("/api/**", "/user").hasRole(Role.USER.name()) // 사용자만 허용
                .antMatchers("/my-login", "/store-events", "/about").permitAll() // 모두 허용
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .formLogin().loginPage("/my-login")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}