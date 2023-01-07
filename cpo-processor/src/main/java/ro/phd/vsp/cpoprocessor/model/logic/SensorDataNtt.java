package ro.phd.vsp.cpoprocessor.model.logic;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class SensorDataNtt {

  private UUID sid;
  private UUID kid;
  private SensorMetadataNtt metadata;
  private CommandConfigNtt cmdConfig;
  private Double value;
}
