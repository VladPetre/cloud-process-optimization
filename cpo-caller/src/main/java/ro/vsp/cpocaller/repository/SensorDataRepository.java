package ro.vsp.cpocaller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.vsp.cpocaller.model.SensorData;

public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {

}
