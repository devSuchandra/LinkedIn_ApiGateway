package com.CodingShuttle.LinkedIn.Api_Gateway.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {
        return GatewayRouterFunctions.route("UserService")
                .route(RequestPredicates.path("/api/v1/user-service/**"), HandlerFunctions.http())
                .filter(FilterFunctions.stripPrefix(2))
                .filter(LoadBalancerFilterFunctions.lb("USERSERVICE"))
                .build()
                .and(GatewayRouterFunctions.route("PostService")
                        .route(RequestPredicates.path("/api/v1/post-service/**"), HandlerFunctions.http())
                        .filter(FilterFunctions.stripPrefix(2))
                        .filter(LoadBalancerFilterFunctions.lb("POSTSERVICE"))
                        .build())
                .and(GatewayRouterFunctions.route("JobService")
                        .route(RequestPredicates.path("/api/v1/job-service/**"), HandlerFunctions.http())
                        .filter(FilterFunctions.stripPrefix(2))
                        .filter(LoadBalancerFilterFunctions.lb("JOBSERVICE"))
                        .build())
                .and(GatewayRouterFunctions.route("ConnectionService")
                        .route(RequestPredicates.path("/api/v1/connection-service/**"), HandlerFunctions.http())
                        .filter(FilterFunctions.stripPrefix(2))
                        .filter(LoadBalancerFilterFunctions.lb("CONNECTIONSERVICE"))
                        .build());
    }
}
