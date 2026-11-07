package com.example.springsecuritytest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails user = User.builder()
                .username("test")
                .password(encoder.encode("test"))
                //.roles("ADMIN") //roles og authorities gør det samme,
                // men beskriver rollerne marginalt anderledes roles("USER") bliver til authorities("ROLE_USER")
                //så du skal huske op du slår rollerne op med hasRole() eller hasAuthority at der kan forkomme uhensigsmæssige problemer
                .authorities("ROLE_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
