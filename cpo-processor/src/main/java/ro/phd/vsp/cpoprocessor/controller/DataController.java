package ro.phd.vsp.cpoprocessor.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.phd.vsp.cpocommon.dtos.SensorValueDTO;
import ro.phd.vsp.cpoprocessor.service.ProcessService;

@RestController
@RequestMapping("data")
@RequiredArgsConstructor
public class DataController {

  private final ProcessService processService;

  @PostMapping
  public void processData(@RequestBody SensorValueDTO sensorValueDTO)
  {
    processService.process(sensorValueDTO);
  }

}
