package ro.phd.vsp.cpoprocessor.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ro.phd.vsp.cpocommon.dtos.CommandConfigDTO;
import ro.phd.vsp.cpocommon.dtos.SensorDataDTO;
import ro.phd.vsp.cpocommon.dtos.SensorMetadataDTO;
import ro.phd.vsp.cpocommon.dtos.SensorValueDTO;
import ro.phd.vsp.cpoprocessor.model.CrtSensorData;
import ro.phd.vsp.cpoprocessor.model.CrtSensorMetadata;
import ro.phd.vsp.cpoprocessor.model.SensorData;
import ro.phd.vsp.cpoprocessor.model.SensorMetadata;
import ro.phd.vsp.cpoprocessor.model.logic.CommandConfigNtt;
import ro.phd.vsp.cpoprocessor.model.logic.SensorDataNtt;
import ro.phd.vsp.cpoprocessor.model.logic.SensorMetadataNtt;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DomainMapper {

  public static SensorDataNtt dtoToNtt(SensorDataDTO dto)
  {
    return SensorDataNtt.builder()
        .sid(dto.sid())
        .kid(dto.kid())
        .metadata(dtoToNtt(dto.metadata()))
        .cmdConfig(dtoToNtt(dto.cmdConfig()))
        .build();
  }

  public static SensorMetadataNtt dtoToNtt(SensorMetadataDTO dto)
  {
    return SensorMetadataNtt.builder()
        .sensorType(dto.sensorType())
        .measureType(dto.measureType())
        .measureUnit(dto.measureUnit())
        .build();
  }

  public static CommandConfigNtt dtoToNtt(CommandConfigDTO dto)
  {
    return CommandConfigNtt.builder()
        .cmd(dto.cmd())
        .cmdRule(dto.cmdRule())
        .cmdType(dto.cmdType())
        .multiplier(dto.multiplier())
        .priority(dto.priority())
        .refValue(dto.refValue())
        .build();
  }

  public static void fillValues(SensorValueDTO value, SensorDataNtt data) {
    data.setValue(value.value());
    data.getMetadata().setBatteryLevel(value.batteryLevel());
    data.getMetadata().setSignalStrength(value.signalStrength());
    data.getMetadata().setUpdatedOn(value.updatedOn());
  }

  public static CrtSensorData nttToCrtData(SensorDataNtt ntt){
    return CrtSensorData.builder()
        .sid(ntt.getSid())
        .dvalue(ntt.getValue())
        .updatedOn(ntt.getMetadata().getUpdatedOn())
        .build();
  }

  public static SensorData nttToData(SensorDataNtt ntt){
    return SensorData.builder()
        .sid(ntt.getSid())
        .dvalue(ntt.getValue())
        .updatedOn(ntt.getMetadata().getUpdatedOn())
        .build();
  }

  public static CrtSensorMetadata nttTOCrtMetadata(SensorDataNtt ntt){
    return CrtSensorMetadata.builder()
        .sid(ntt.getSid())
        .sensorType(ntt.getMetadata().getSensorType())
        .status(1)
        .batteryLevel(ntt.getMetadata().getBatteryLevel())
        .signalStrength(ntt.getMetadata().getSignalStrength())
        .updateOn(ntt.getMetadata().getUpdatedOn())
        .build();
  }

  public static SensorMetadata nttTOMetadata(SensorDataNtt ntt){
    return SensorMetadata.builder()
        .sid(ntt.getSid())
        .sensorType(ntt.getMetadata().getSensorType())
        .status(1)
        .batteryLevel(ntt.getMetadata().getBatteryLevel())
        .signalStrength(ntt.getMetadata().getSignalStrength())
        .updatedOn(ntt.getMetadata().getUpdatedOn())
        .build();
  }

}
