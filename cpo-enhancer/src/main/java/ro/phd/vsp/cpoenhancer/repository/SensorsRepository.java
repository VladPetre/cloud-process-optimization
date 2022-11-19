package ro.phd.vsp.cpoenhancer.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.phd.vsp.cpoenhancer.models.Sensor;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, UUID> {

}
