package ro.phd.vsp.cpocommon.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public record SensorValueDTO(UUID sid,
                             Double value,
                             Double batteryLevel,
                             Double signalStrength,
                             LocalDateTime updatedOn) {

}
