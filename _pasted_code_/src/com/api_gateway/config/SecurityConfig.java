package com.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFilterChain;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // Constructor injection for your filter
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
        return http
            .csrf(csrf -> csrf.disable())
            // YAHAN HUMNE JWT FILTER KO REGISTER KIYA HAI
            .addFilterAt(jwtAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange(ex -> ex
                // PUBLIC
                .pathMatchers("/auth/register", "/auth/login", "/products/getall").permitAll()

                // ADMIN
                .pathMatchers(
                    "/inventory/restock", "/order/getall", "/user/getall",
                    "/cart/getall", "/wishlist/getall", "/products/add"
                ).hasRole("ADMIN")

                // USER
                .pathMatchers(
                    "/order/create", "/order/check", "/user/getone",
                    "/user/deletecartitem", "/cart/add", "/cart/remove",
                    "/wishlist/add", "/wishlist/remove"
                ).hasRole("USER")

                // fallback
                .anyExchange().authenticated()
            )
            .build();
    }
}