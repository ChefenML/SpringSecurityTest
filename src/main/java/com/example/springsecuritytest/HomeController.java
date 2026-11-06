package com.example.springsecuritytest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Burde alle ku se.... /secret on the other hand....";
    }

    @GetMapping("/secret")
    public String secret(@AuthenticationPrincipal UserDetails userDetails) {
        return "Hemmelige side, kun for fancy pants. <BR> User: "
               + userDetails.getUsername() + "<BR> Password: "
                + userDetails.getPassword() + "<BR> AccountStatus: "
                + userDetails.getAuthorities() + "<BR> Enabled: "
                + userDetails.isEnabled() + "<BR> AccountNonExpired: "
                + userDetails.isAccountNonExpired() + "<BR> AccountNonLocked: "
                + userDetails.isAccountNonLocked() + "<BR> CredentialsNonExpired: "
                + userDetails.isCredentialsNonExpired();
    }
}
