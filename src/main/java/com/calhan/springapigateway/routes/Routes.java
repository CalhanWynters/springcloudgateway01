package com.calhan.springapigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class Routes {

    // This is a RouterFunction to test on local machine in development stage.
    @Bean
    public RouterFunction<ServerResponse> testServiceRoute() {
        return GatewayRouterFunctions.route("test_service")
                .route(RequestPredicates.path("/api/test"), HandlerFunctions.http())
                .before(BeforeFilterFunctions.uri("http://localhost:8080"))
                .build();
    }

    /*
    ** This is an example of a production ready RouterFunction.
    ** Use this when ready to release into production stage.

    @Bean
    public RouterFunction<ServerResponse> productServiceRoute() {
        return GatewayRouterFunctions.route("product_service")
                .route(RequestPredicates.path("/api/product"), HandlerFunctions.http()) // Use the parameter-less method
                .before(BeforeFilterFunctions.uri("lb://product-service"))            // Use a filter to set the URI
                .build();
    }

     */
}
