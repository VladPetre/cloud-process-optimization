package ro.vsp.cpocaller.utils;

import java.util.Arrays;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@UtilityClass
public class HttpUtils {

  /**
   * Build header for Resttemplate request
   *
   * @return httpHeaders
   */
  public static HttpHeaders buildRTHeaders(String instanceId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.set("instance_id", instanceId);
    return headers;
  }

}
