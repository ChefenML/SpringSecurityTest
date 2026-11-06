package com.example.springsecuritytest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/public").permitAll() // "/" + "/public" er for alle
                        .anyRequest().authenticated()               // Alt andet kræver login
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/secret", true)         // path hvor man lander efter succesfuldt login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build();
    }

// alle kan se disse sider / & /public, hvis under sider skal tilføjes markeres de med én stjerne for KUN den /under-aktuel/* og 2 stjerner for ALLE undersider /under/**          */
//Hvor bliver man redirected til ved succesfuldt login: /secret                 */
// Hvor bliver man redirected til ved succesfuldt logout: /                 */
}




//
//                     http
//                             .authorizeHttpRequests(auth -> auth
//                             .requestMatchers("/","public").permitAll() <- for alle
//                             .anyRequest.authenticated()) <- anything else needs AUTH
//        .formLogin(form -> form.defaultSuccessUrl("/secret",true))
//        .logout(logout -> logout.logoutSuccessUrl("/").permitAll());
//
//        return http.build();