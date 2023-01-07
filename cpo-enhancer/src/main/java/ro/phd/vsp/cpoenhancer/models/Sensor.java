package ro.phd.vsp.cpoenhancer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.UUID;

@Entity(name = "sensors")
@Table(name = "sensors")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Sensor {

    @Id
    private UUID sid;
    private String sensorType;
    private String measureType;
    private String measureUnit;

    @ManyToOne(fetch = FetchType.LAZY)
    private Kit kit;
}
