package ro.vsp.cpocaller.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "execution_status")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecutionStatus {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private UUID instanceId;
  private String instanceType;
  private LocalDateTime startedAt;
  private LocalDateTime finishedAt;
  private Integer error; //0 - none, 1 - exception

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "execution_step_id")
  private ExecutionStep executionStep;
}
