package org.example.apigateway.config;

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

  @Bean
  public RouteLocator createRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(productsServiceId, route -> route.path(productsServicePath).uri(productsServiceUrl))
      .build();
  }
}
