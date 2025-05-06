package com.example.public_issue_reporter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // désactive CSRF (utile pour API REST)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // toute requête nécessite d’être authentifié
            )
            .httpBasic(); // utilise HTTP Basic Auth (login/mot de passe dans l'en-tête)

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails admin = User.withUsername("edidi")
                .password(passwordEncoder().encode("edidi123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("bajou")
                .password(passwordEncoder().encode("bajou123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // encode les mots de passe en Bcrypt
    }
}
