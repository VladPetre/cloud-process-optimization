package ro.phd.vsp.cpoprocessor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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

@Entity(name = "sensor_data")
@Table(name = "sensor_data")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class SensorData {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private UUID sid;
  private Double dvalue;
  private LocalDateTime updatedOn;
}
