package ro.phd.vsp.cpogateway.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${cpo.services.auth.uri}")
  public String authUrl;

  @Bean
  public WebClient authWebClient() {
    return WebClient.builder()
        .baseUrl(authUrl)
        .build();
  }

}
