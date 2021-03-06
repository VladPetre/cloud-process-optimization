package ro.vsp.cpocaller.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ro.vsp.cpocaller.model.Sensor;

public interface SensorsRepository extends JpaRepository<Sensor, UUID> {

  @Modifying
  @Query(value = "update sensors  set status = ?1 where guid = ?2", nativeQuery = true)
  Integer updateLastActive(Integer status, UUID sensorId);
}
