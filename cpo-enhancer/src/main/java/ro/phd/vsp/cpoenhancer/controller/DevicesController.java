package ro.phd.vsp.cpoenhancer.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ro.phd.vsp.cpocommon.dtos.RegisteredDevicesDTO;
import ro.phd.vsp.cpocommon.dtos.SensorDataDTO;
import ro.phd.vsp.cpoenhancer.service.DevicesService;

@RestController
@RequestMapping("devices")
@ResponseBody
@RequiredArgsConstructor
public class DevicesController {

  private final DevicesService devicesService;

  @GetMapping("/{kid}/links")
  public RegisteredDevicesDTO getRegisteredDevices(@PathVariable String kid) {
    return devicesService.getRegisteredDevicesList(kid);
  }

  @GetMapping("/{sid}")
  public SensorDataDTO enrichSensorMetadata(@PathVariable UUID sid) {
    return devicesService.enrichSensorMetadata(sid);
  }
}
