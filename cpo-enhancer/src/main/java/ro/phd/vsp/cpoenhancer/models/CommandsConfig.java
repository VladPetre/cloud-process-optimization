package ro.phd.vsp.cpoenhancer.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "commands_configs")
@Table(name = "commands_configs")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CommandsConfig {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String sensorType;
  private String location;

  private String cmdRule; // stddev/proc of mean value/threshold
  private String cmdType; // alerta/comanda sau ambele
  private Double multiplier; // multiplier for stddev or the proc allowed for mean value
  private Double lowVal; // for threshold
  private Double highVal; // for threshold

}
