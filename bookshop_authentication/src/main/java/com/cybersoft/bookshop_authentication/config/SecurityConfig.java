package com.cybersoft.bookshop_authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
            .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection for simplicity
            .cors(AbstractHttpConfigurer::disable) // Disable CORS for simplicity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/sign-in").permitAll() // Allow public access to sign-in endpoint
                .anyRequest().authenticated() // Require authentication for all other requests
            );
        return http.build();
    }
}
