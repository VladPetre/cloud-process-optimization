package ro.phd.vsp.cpoenhancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.phd.vsp.cpoenhancer.models.Kit;

import java.util.UUID;

@Repository
public interface KitsRepository extends JpaRepository<Kit, UUID> {
}
