package ro.phd.vsp.cpoprocessor.model.logic;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SensorMetadataNtt {
  private String sensorType;
  private String measureType;
  private String measureUnit;
  private Double batteryLevel;
  private Double signalStrength;
  private LocalDateTime updatedOn;
}
