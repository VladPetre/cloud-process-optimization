package ro.phd.vsp.cpoenhancer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

  private String cmd;
  private String cmdRule; // stddev/proc of mean value/threshold
  private String cmdType; // notificare/alerta sau comanda sau ambele (comanda poate fi si acustica si mecnaica - inchide o valva, porneste un ventilator)
  private Double multiplier; // multiplier for stddev or the proc allowed for mean value
  private Double refValue;
  private String priority;

}
