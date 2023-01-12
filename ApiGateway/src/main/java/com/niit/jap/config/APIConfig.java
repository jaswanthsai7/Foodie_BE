package com.niit.jap.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;


@Configuration
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class APIConfig {
    @Bean
    public RouteLocator getRoutes(RouteLocatorBuilder builder) {
        return builder.routes().route(p -> p.path("/api/v1/**").uri("http://authentication-service:8084")).route(p -> p.path("/api/v2/**").uri("http://restaurant-service:8085")).route(p -> p.path("/api/v3/**").uri("http://favourite-service:8086")).route(p -> p.path("/api/v4/**").uri("http://order-service:8087")).build();
    }

}
