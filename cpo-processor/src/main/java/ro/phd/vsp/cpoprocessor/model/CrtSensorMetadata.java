package ro.phd.vsp.cpoprocessor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "crt_sensor_metadata")
@Table(name = "crt_sensor_metadata")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CrtSensorMetadata {

  @Id
  private UUID sid;
  private String sensorType;
  private Integer status;
  private Double batteryLevel;
  private Double signalStrength;
  private LocalDateTime updateOn;
}
