package ro.vsp.cpocaller.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ro.vsp.cpocaller.model.ExecutionStep;
import ro.vsp.cpocaller.repository.ExecutionStepsRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExecutionRegistrationService {

  private final ExecutionStepsRepository executionStepsRepository;


  /**
   * Get execution step configuration for RT caller where is active and last_active is no older than
   * a number of seconds whithin step
   *
   * @return Optional<ExecutionStep>
   */
  public Optional<ExecutionStep> getRTStepToExecute() {

    List<ExecutionStep> activeSteps = executionStepsRepository
        .findAll(Sort.by(Sort.Direction.ASC, "id"))
        .stream()
        .filter(s -> !s.getMethod().toUpperCase().contains("REACT_"))
        .filter(ExecutionStep::getActive)
        .filter(s -> s.getLastActive().until(LocalDateTime.now(), ChronoUnit.SECONDS) <= s
            .getSecondsOffset())
        .collect(Collectors.toList());

    if (activeSteps.size() != 1) {
      log.error("RT: None or Many steps were active!");
      throw new IllegalArgumentException("None or Many steps were active!");
    }

    return Optional.of(activeSteps.get(0));
  }

  /**
   * Get execution step configuration for REACT caller where is active and last_active is no older
   * than a number of seconds whithin step
   *
   * @return Optional<ExecutionStep>
   */
  public Optional<ExecutionStep> getReactStepToExecute() {

    List<ExecutionStep> activeSteps = executionStepsRepository
        .findAll(Sort.by(Sort.Direction.ASC, "id"))
        .stream()
        .filter(s -> s.getMethod().toUpperCase().contains("REACT_"))
        .filter(ExecutionStep::getActive)
        .filter(s -> s.getLastActive().until(LocalDateTime.now(), ChronoUnit.SECONDS) <= s
            .getSecondsOffset())
        .collect(Collectors.toList());

    if (activeSteps.size() != 1) {
      log.error("REACT: None or Many steps were active!");
      throw new IllegalArgumentException("None or Many steps were active!");
    }

    return Optional.of(activeSteps.get(0));
  }
}
