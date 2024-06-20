package com.service.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes().route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("patient",
                        r -> r.path("/patient/**").filters(f -> f.filter(filter)).uri(
                                "http://localhost:8081"))
                .route("note",
                        r -> r.path("/note/**").filters(f -> f.filter(filter)).uri(
                                "http://localhost:8082"))
                .route("note",
                        r -> r.path("/risk/**").filters(f -> f.filter(filter)).uri(
                                "http://localhost:8083"))
                .build();
    }

}