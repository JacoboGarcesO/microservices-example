package org.example.apigateway.config;

import org.example.apigateway.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {
  @Value("${products-service.url}") private String productsServiceUrl;
  @Value("${products-service.id}") private String productsServiceId;
  @Value("${products-service.path}") private String productsServicePath;

  @Value("${carts-service.url}") private String cartsServiceUrl;
  @Value("${carts-service.id}") private String cartsServiceId;
  @Value("${carts-service.path}") private String cartsServicePath;

  private final JwtAuthenticationFilter filter;

  public RouteConfig(JwtAuthenticationFilter filter) {
    this.filter = filter;
  }

  @Bean
  public RouteLocator createRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(productsServiceId, route -> route.path(productsServicePath).filters(gtf -> gtf.filter(filter)).uri(productsServiceUrl))
      .route(cartsServiceId, route -> route.path(cartsServicePath).filters(gtf -> gtf.filter(filter)).uri(cartsServiceUrl))
      .route("auth-service", route -> route.path("/api/auth/**").uri("http://localhost:8084"))
      .build();
  }
}
