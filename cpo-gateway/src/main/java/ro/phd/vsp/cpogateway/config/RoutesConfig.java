package ro.phd.vsp.cpogateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.phd.vsp.cpogateway.config.AuthFilter.Config;

@Configuration
public class RoutesConfig {

  @Value("${cpo.services.processor.uri}")
  public String processorUrl;


  @Bean
  RouteLocator buildRoutes(RouteLocatorBuilder routesBuilder, AuthFilter authFilter) {
    return routesBuilder.routes()
        .route("processor", spec ->
            spec.path("/data/**")
                .filters(
                    f -> f.setPath("/data")
//                        .filter(new OrderedGatewayFilter(new LoggingFilter(), Integer.MAX_VALUE))
                        .filter(new OrderedGatewayFilter(authFilter.apply(new Config()),
                            Integer.MAX_VALUE))
                )
                .uri(processorUrl)
        ).build();
  }

}
