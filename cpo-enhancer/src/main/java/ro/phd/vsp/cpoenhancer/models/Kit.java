package ro.phd.vsp.cpoenhancer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

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