package ro.phd.vsp.cpocommon.dtos;

import lombok.Builder;

@Builder
public record CommandConfigDTO(String cmd,
                               String cmdRule,
                               String cmdType,
                               Double multiplier,
                               Double refValue,
                               String priority) {

}
