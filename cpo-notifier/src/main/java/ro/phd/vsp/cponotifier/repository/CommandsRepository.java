package ro.phd.vsp.cponotifier.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.phd.vsp.cponotifier.model.Command;

@Repository
public interface CommandsRepository extends JpaRepository<Command, Long> {

}
