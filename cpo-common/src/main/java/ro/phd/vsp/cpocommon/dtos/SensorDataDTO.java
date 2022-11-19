package ro.phd.vsp.cpocommon.dtos;

import java.util.UUID;
import lombok.Builder;

@Builder
public record SensorDataDTO(UUID sid,
                            UUID kid,
                            SensorMetadataDTO metadata,
                            CommandConfigDTO cmdConfig,
                            Double value ) {}
