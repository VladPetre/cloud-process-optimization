package ro.phd.vsp.cpocommon.dtos;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CommandDTO(String cmd,
                         String reason,
                         LocalDateTime createdOn,
                         Boolean executed,
                         Integer retries) {

}
