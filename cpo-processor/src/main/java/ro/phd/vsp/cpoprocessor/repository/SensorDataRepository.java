package ro.phd.vsp.cpoprocessor.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.phd.vsp.cpoprocessor.model.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, UUID> {

  List<SensorData> findByIdAndUpdatedOnGreaterThanEqual(UUID id, LocalDateTime ldt);

}
