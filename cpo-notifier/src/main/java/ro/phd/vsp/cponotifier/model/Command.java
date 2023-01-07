package ro.phd.vsp.cponotifier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "commands")
@Table(name = "commands")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Command {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String cmd;
  private String reason;
  private LocalDateTime createdOn;
  private Boolean executed;
  private Integer retries;

}
