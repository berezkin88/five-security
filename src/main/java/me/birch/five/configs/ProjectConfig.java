package me.birch.five.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .formLogin(customizer -> customizer.defaultSuccessUrl("/main", true))
            .authorizeHttpRequests(customizer -> customizer.anyRequest().authenticated())
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        var encodersMap = Map.of(
            "bcrypt", new BCryptPasswordEncoder(),
            "scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()
        );

        return new DelegatingPasswordEncoder("bcrypt", encodersMap);
    }
}
