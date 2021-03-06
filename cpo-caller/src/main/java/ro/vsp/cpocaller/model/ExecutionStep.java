package ro.vsp.cpocaller.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "execution_steps")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionStep {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer threadsNumber;
  private Integer entriesNumber;
  private String method;
  private Integer status;
  private Boolean active;
  private LocalDateTime lastActive;
  private Integer receiverNrInstances;
  private Long secondsOffset;
}
