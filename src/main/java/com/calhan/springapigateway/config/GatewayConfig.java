package com.calhan.springapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${POSTS_ROUTE_URI:http://localhost:8081}")
    private String postsUri;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("posts-route", r -> r
                        .path("/posts/**")
                        .filters(f -> f
                                .prefixPath("/api")
                                .addResponseHeader("X-App", "gateway")
                        )
                        .uri(postsUri)
                )
                .build();
    }
}