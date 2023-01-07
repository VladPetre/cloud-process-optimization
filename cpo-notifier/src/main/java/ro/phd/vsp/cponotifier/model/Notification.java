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

@Entity(name = "notifications")
@Table(name = "notifications")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Notification {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String notificationType;
  private String sendTo;
  private String message;
  private LocalDateTime createdOn;
  private Boolean sent;
  private Integer retries;

}
