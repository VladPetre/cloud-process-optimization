package ro.phd.vsp.cpocommon.dtos;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SensorMetadataDTO(String sensorType,
                                String measureType,
                                String measureUnit,
                                Double batteryLevel,
                                Double signalStrength,
                                LocalDateTime updatedOn) {

}
