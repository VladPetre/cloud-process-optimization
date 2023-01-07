package ro.phd.vsp.cpogateway.config;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR;

import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ro.phd.vsp.cpogateway.exception.UnauthorizedException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

  private final WebClient authWebClient;

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
        log.error("Missing auth information");
        throw new UnauthorizedException("Missing auth information");
      }

      String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
      String[] parts = authHeader.split(" ");
      if (parts.length != 2 || !"Bearer".equals(parts[0])) {
        log.error("Wrong auth information");
        throw new UnauthorizedException("Wrong auth information");
      }

      Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
      String originalUri = (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
      String[] subpaths = originalUri.split("/");
      UUID sid;
      try {
        sid = UUID.fromString(subpaths[subpaths.length - 1]);
      } catch (Exception e) {
        log.error("Wrong path identifier");
        throw new UnauthorizedException("Wrong path identifier");
      }

      return authWebClient
          .get()
          .uri("auth/" + sid)
          .header(HttpHeaders.AUTHORIZATION, authHeader)
          .retrieve()
          .bodyToMono(Void.class)
          .map(s -> exchange)
          .flatMap(chain::filter);
    };
  }

  public static class Config {

  }
}
