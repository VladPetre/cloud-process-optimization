package ro.phd.vsp.cpoauth.service;

import static ro.phd.vsp.cpoauth.utils.HttpUtils.buildHeaders;
import static ro.phd.vsp.cpocommon.utils.Constants.KIT_ID_CLAIM_NAME;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ro.phd.vsp.cpocommon.dtos.RegisteredDevicesDTO;
import ro.phd.vsp.cpocommon.exception.UnauthorizedException;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final Map<UUID, Set<UUID>> registeredDevices = new HashMap<>();
    private final WebClient webClient;

    @Value("${cpo.jwt.signing-key}")
    private String signingKey;

    @Value("${cpo.jwt.issuer}")
    private String issuer;

    /**
     * Authorize the device: check if JWT Token is valid and if the device is registered to a kit
     *
     * @param token - JWT token
     * @param sid   - Device ID
     */
    public void authorize(String token, String sid) {
        Claims claims = validateToken(token);

        if (!claims.containsKey(KIT_ID_CLAIM_NAME)) {
            throw new UnauthorizedException("Invalid KIT_ID claim.");
        }

        Object kitIdClaim = claims.get(KIT_ID_CLAIM_NAME);
        if (Objects.nonNull(kitIdClaim) && !isDeviceRegistered(kitIdClaim.toString(), sid)) {
            throw new UnauthorizedException("Device not registered.");
        }
    }

    private Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Base64.getDecoder().decode(signingKey))
                .requireIssuer(new String(Base64.getDecoder().decode(issuer)))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isDeviceRegistered(String kid, String sid) {
        return getRegisteredDevicesByKitId(kid)
                .map(s -> s.contains(UUID.fromString(sid)))
                .orElseThrow(UnauthorizedException::new);
    }

    private Optional<Set<UUID>> getRegisteredDevicesByKitId(String key) {
        if (registeredDevices.entrySet().isEmpty()) {
            registeredDevices.putAll(updateCacheFromEnhancer());
        }

        return Optional.of(registeredDevices.get(UUID.fromString(key)));
    }

    private Map<UUID, Set<UUID>> updateCacheFromEnhancer() {
        log.info("Retrieving registered devices from cpo-enhancer...");

        return webClient.get().uri("/devices/links")
                .headers(h -> buildHeaders("guid.toString()"))
                .retrieve()
                .bodyToMono(RegisteredDevicesDTO.class)
                .map(RegisteredDevicesDTO::registeredDevices)
                .block(Duration.of(500, ChronoUnit.MILLIS));
    }

}
