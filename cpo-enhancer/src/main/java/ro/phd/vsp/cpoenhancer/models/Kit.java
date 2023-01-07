package ro.phd.vsp.cpoenhancer.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "kits")
@Table(name = "kits")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Kit {

  @Id
  private UUID kid;
  private String kitName;
  private String kitLocation;

  @OneToMany(
      mappedBy = "kit",
      cascade = CascadeType.ALL,
      orphanRemoval = true
  )
  private Set<Sensor> sensors;
}