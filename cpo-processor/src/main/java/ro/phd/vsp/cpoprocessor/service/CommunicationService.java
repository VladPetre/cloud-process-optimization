package ro.phd.vsp.cpoprocessor.service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ro.phd.vsp.cpocommon.dtos.AlertDTO;
import ro.phd.vsp.cpocommon.dtos.SensorDataDTO;

@Service
public class CommunicationService {

  private final WebClient enhancerClient;

  private final WebClient notifierClient;

  public CommunicationService(@Qualifier("enhancer-client") WebClient enhancerClient,
      @Qualifier("notifier-client") WebClient notifierClient) {
    this.enhancerClient = enhancerClient;
    this.notifierClient = notifierClient;
  }

  public SensorDataDTO enrichSensorData(UUID sid) {
    return enhancerClient.get().uri("/devices/" + sid)
        .retrieve()
        .bodyToMono(SensorDataDTO.class)
        .block(Duration.of(500, ChronoUnit.MILLIS));
  }

  public void sendAlert(AlertDTO alert) {
    notifierClient.post().uri("alerts")
        .body(Mono.just(alert), AlertDTO.class)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }
}
