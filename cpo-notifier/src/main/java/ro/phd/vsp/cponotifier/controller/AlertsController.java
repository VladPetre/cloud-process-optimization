package ro.phd.vsp.cponotifier.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.phd.vsp.cpocommon.dtos.AlertDTO;
import ro.phd.vsp.cponotifier.service.NotificationService;

@RestController
@RequestMapping("/alerts")
@RequiredArgsConstructor
@ResponseBody
public class AlertsController {

  private final NotificationService notificationService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AlertDTO processNotification(@RequestBody AlertDTO alertDTO) {
    return notificationService.processNotification(alertDTO);
  }

}
