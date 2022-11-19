package ro.phd.vsp.cpoauth.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpUtils {

    /**
     * Build header for HTTP request
     *
     * @return httpHeaders
     */
    public static HttpHeaders buildHeaders(String instanceId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("instance_id", instanceId);
        return headers;
    }

}
