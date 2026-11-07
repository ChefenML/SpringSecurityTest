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

    @GetMapping("/user-dashboard")
    public String secretUser(@AuthenticationPrincipal UserDetails userDetails) {
        return "Hemmelige side, kun for fancy pants. " +
                "<BR> User: " + userDetails.getUsername() +
                "<BR> Password: " + userDetails.getPassword() +
                "<BR> AccountStatus: " + userDetails.getAuthorities() +
                "<BR> Enabled: " + userDetails.isEnabled() +
                "<BR> AccountNonExpired: " + userDetails.isAccountNonExpired() +
                "<BR> AccountNonLocked: " + userDetails.isAccountNonLocked() +
                "<BR> CredentialsNonExpired: " + userDetails.isCredentialsNonExpired();
    }

    @GetMapping("/admin-dashboard")
    public String secretAdmin(@AuthenticationPrincipal UserDetails userDetails) {
        return "Hemmelige side, kun for fancy Admins. " +
                "<BR> User: " + userDetails.getUsername() +
                "<BR> Password: " + userDetails.getPassword() +
                "<BR> AccountStatus: " + userDetails.getAuthorities() +
                "<BR> Enabled: " + userDetails.isEnabled() +
                "<BR> AccountNonExpired: " + userDetails.isAccountNonExpired() +
                "<BR> AccountNonLocked: " + userDetails.isAccountNonLocked() +
                "<BR> CredentialsNonExpired: " + userDetails.isCredentialsNonExpired();
    }

    @GetMapping("/public")
    public String publicPage() {
        return "Dette er en offentlig side, som alle kan se uden login. Ikke som judas <a href=" + "http://localhost:8080/secret" + ">/secret</a>";
    }

    @GetMapping("/logout")
    public String logout() {
        return "You're out of here aka logged out.... <a href=" + "http://localhost:8080/secret" + ">/secret</a>";
    }

    @GetMapping("/looksie")
    public String login() {
        return "Wanna try";
    }

}
