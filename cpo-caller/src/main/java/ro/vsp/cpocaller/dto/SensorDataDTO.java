package ro.vsp.cpocaller.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SensorDataDTO {
  private UUID guid;
  private Double value;
  private Double battery;
  private LocalDateTime updatedOn;
}
