package ro.vsp.cpocaller;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {"ro.vsp.cpocaller"})
public class AppConfiguration {

  @Value("${CPO_GATEWAY_URI}")
  public String gatewayURI;

  /**
   * Generate unique UUID to identify each instance. Used for execution step table as key of status
   * table for caller
   *
   * @return UUID.randomUUID()
   */
  @Bean
  public UUID uniqueInstanceUUID() {
    return UUID.randomUUID();
  }

  /**
   * Return resttemplate object bean with base url
   *
   * @return resttemplate object
   */
  @Bean
  public RestTemplate restTemplate() {

    return new RestTemplateBuilder()
        .rootUri(gatewayURI)
        .build();
  }

}
