package ro.phd.vsp.cpoprocessor.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.phd.vsp.cpoprocessor.model.SensorMetadata;

@Repository
public interface SensorMetadataRepository extends JpaRepository<SensorMetadata, UUID> {

}
