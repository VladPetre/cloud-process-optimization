package ro.phd.vsp.cpocommon.dtos;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record NotificationDTO(String notificationType,
                              String sendTo,
                              String message,
                              LocalDateTime createdOn,
                              Boolean sent,
                              Integer retries) {

}
