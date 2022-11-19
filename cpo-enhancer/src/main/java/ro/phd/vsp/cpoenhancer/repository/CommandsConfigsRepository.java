package ro.phd.vsp.cpoenhancer.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.phd.vsp.cpoenhancer.models.CommandsConfig;

@Repository
public interface CommandsConfigsRepository extends JpaRepository<CommandsConfig, Long> {
  Optional<CommandsConfig> findBySensorTypeAndLocation(String sensorType, String location);
}
