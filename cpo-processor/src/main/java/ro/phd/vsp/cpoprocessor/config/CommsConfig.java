package ro.phd.vsp.cpoprocessor.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class CommsConfig {

  @Value("${cpo.services.enhancer.uri}")
  public String enhancerURL;

  @Value("${cpo.services.notifier.uri}")
  public String notifierURL;

  @Bean
  @Qualifier("enhancer-client")
  public WebClient enahnacerClient() {
    return WebClient.builder()
        .baseUrl(enhancerURL)
        .build();
  }

  @Bean
  @Qualifier("notifier-client")
  public WebClient notifierClient() {
    return WebClient.builder()
        .baseUrl(notifierURL)
        .build();
  }

}
