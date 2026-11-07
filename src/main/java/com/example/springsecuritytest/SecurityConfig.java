package com.example.springsecuritytest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableWebSecurity //kun for ældre versioner af spring boot
public class SecurityConfig {

    // Custom login stuff, tjek klassen
    private final CustomLoginSuccessHandler successHandler;

    public SecurityConfig(CustomLoginSuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public").permitAll()
                        .requestMatchers("/admin-dashboard/**").hasRole("ADMIN")// Admin har side for sig
                        .anyRequest().authenticated() // Alt andet kan tilgås med normalt login
                )
                .formLogin(form -> form
                        .successHandler(successHandler) // path hvor man lander efter succesfuldt login, her bruger vi custom solution for at skælne mellem admin og user
                        .permitAll()
                )
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

}

