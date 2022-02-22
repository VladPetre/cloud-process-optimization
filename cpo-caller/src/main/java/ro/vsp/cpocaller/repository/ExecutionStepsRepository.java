package ro.vsp.cpocaller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.vsp.cpocaller.model.ExecutionStep;

public interface ExecutionStepsRepository extends JpaRepository<ExecutionStep, Integer> {


}
