package ro.vsp.cpocaller.service;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.vsp.cpocaller.model.Sensor;
import ro.vsp.cpocaller.repository.SensorsRepository;

@Component
@RequiredArgsConstructor
public class SensorsService {

  private final SensorsRepository sensorsRepository;

  public List<Sensor> getAllSensors() {
    return sensorsRepository.findAll();
  }

  @Transactional
  public Integer updateSensorStatus(Integer status, UUID sensorId) {
    return sensorsRepository.updateLastActive(status, sensorId);
  }

}
