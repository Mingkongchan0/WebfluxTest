package com.demo.webfluxtest.config;
import com.demo.webfluxtest.config.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Objects;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain setSecure(
            ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/inventory/set").hasRole("ADMIN")
                .pathMatchers("/**").permitAll()
                .and()
                .httpBasic();
        return http.authorizeExchange()
                .pathMatchers("/inventory/set")
                .hasAuthority("ROLE_ADMIN")
                .anyExchange().authenticated()
                .and().formLogin()
                .and().build();

    }
    @Bean
    public SecurityWebFilterChain updateSecure(ServerHttpSecurity http) {
        http.csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/inventory/update").hasRole("ADMIN")
                .pathMatchers("/**").permitAll()
                .and()
                .httpBasic();
        return http.authorizeExchange()
                .pathMatchers("/inventory/update")
                .hasAuthority("ROLE_ADMIN")
                .anyExchange().authenticated()
                .and().formLogin()
                .and().build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public MapReactiveUserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("user")
                .password(Objects.requireNonNull(passwordEncoder().encode("user")))
                .roles("USER")
                .build();
        UserDetails admin = User
                .withUsername("admin")
                .password(Objects.requireNonNull(passwordEncoder().encode("admin")))
                .roles("ADMIN")
                .build();
        return new MapReactiveUserDetailsService(user, admin);
    }
}
