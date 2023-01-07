package ro.phd.vsp.cpoprocessor.model.logic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class CommandConfigNtt {

  private String cmd;
  private String cmdRule;
  private String cmdType;
  private Double multiplier;
  private Double refValue;
  private String priority;
}
