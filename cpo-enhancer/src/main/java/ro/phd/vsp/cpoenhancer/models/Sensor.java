package ro.phd.vsp.cpoenhancer.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
