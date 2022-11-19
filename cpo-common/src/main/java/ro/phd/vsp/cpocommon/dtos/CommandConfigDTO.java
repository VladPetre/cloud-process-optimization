package ro.phd.vsp.cpocommon.dtos;

import lombok.Builder;

@Builder
public record CommandConfigDTO(String cmdRule,
                               String cmdType,
                               Double multiplier,
                               Double lowVal,
                               Double highVal) {

}
