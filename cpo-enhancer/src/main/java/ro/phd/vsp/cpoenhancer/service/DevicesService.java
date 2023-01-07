package ro.phd.vsp.cpoenhancer.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.phd.vsp.cpocommon.dtos.CommandConfigDTO;
import ro.phd.vsp.cpocommon.dtos.RegisteredDevicesDTO;
import ro.phd.vsp.cpocommon.dtos.SensorDataDTO;
import ro.phd.vsp.cpocommon.dtos.SensorMetadataDTO;
import ro.phd.vsp.cpocommon.exception.DataNotFoundException;
import ro.phd.vsp.cpoenhancer.models.CommandsConfig;
import ro.phd.vsp.cpoenhancer.models.Sensor;
import ro.phd.vsp.cpoenhancer.repository.CommandsConfigsRepository;
import ro.phd.vsp.cpoenhancer.repository.KitsRepository;
import ro.phd.vsp.cpoenhancer.repository.SensorsRepository;

@Component
@RequiredArgsConstructor
public class DevicesService {

  private final KitsRepository kitsRepository;
  private final SensorsRepository sensorsRepository;
  private final CommandsConfigsRepository commandsConfigsRepository;

  public RegisteredDevicesDTO getRegisteredDevicesList(String kid) {
    Map<UUID, Set<UUID>> registeredDevices = new HashMap<>();

    kitsRepository.findById(UUID.fromString(kid))
        .ifPresent(kit -> registeredDevices.put(kit.getKid(),
            kit.getSensors().stream().map(Sensor::getSid).collect(Collectors.toSet())));

    return new RegisteredDevicesDTO(registeredDevices);
  }

  public SensorDataDTO enrichSensorMetadata(UUID sid) {

    Sensor sensor = Optional.of(sensorsRepository.getReferenceById(sid))
        .orElseThrow(() -> new DataNotFoundException(
            String.format("Could not find command config for sid: %s", sid)));

    return SensorDataDTO.builder()
        .sid(sid)
        .kid(sensor.getKit().getKid())
        .metadata(mapMetadata(sensor))
        .cmdConfig(mapCmdConfig(sensor.getSensorType(), sensor.getKit().getKitLocation()))
        .build();
  }

  private SensorMetadataDTO mapMetadata(Sensor sensor) {
    return SensorMetadataDTO.builder()
        .sensorType(sensor.getSensorType())
        .measureType(sensor.getMeasureType())
        .measureUnit(sensor.getMeasureUnit())
        .build();
  }

  private CommandConfigDTO mapCmdConfig(String sensorType, String kitLocation) {
    CommandsConfig config = commandsConfigsRepository.findBySensorTypeAndLocation(sensorType,
            kitLocation)
        .orElseThrow(() -> new DataNotFoundException(
            String.format("Could not find command config for type: %s and loc: %s", sensorType,
                kitLocation)));

    return CommandConfigDTO.builder()
        .cmd(config.getCmd())
        .cmdRule(config.getCmdRule())
        .cmdType(config.getCmdType())
        .multiplier(config.getMultiplier())
        .refValue(config.getRefValue())
        .priority(config.getPriority())
        .build();
  }
}
