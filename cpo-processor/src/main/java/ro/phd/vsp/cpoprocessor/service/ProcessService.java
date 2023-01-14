package ro.phd.vsp.cpoprocessor.service;

import static ro.phd.vsp.cpocommon.utils.Constants.ALERT_COMMAND_TYPE;
import static ro.phd.vsp.cpocommon.utils.Constants.ALERT_NOTIFICATION_TYPE;
import static ro.phd.vsp.cpoprocessor.utils.DomainMapper.dtoToNtt;
import static ro.phd.vsp.cpoprocessor.utils.DomainMapper.fillValues;
import static ro.phd.vsp.cpoprocessor.utils.MathUtils.computeMean;
import static ro.phd.vsp.cpoprocessor.utils.MathUtils.computeStandardDeviation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.phd.vsp.cpocommon.dtos.AlertDTO;
import ro.phd.vsp.cpocommon.dtos.CommandDTO;
import ro.phd.vsp.cpocommon.dtos.NotificationDTO;
import ro.phd.vsp.cpocommon.dtos.SensorValueDTO;
import ro.phd.vsp.cpoprocessor.model.CommandRules;
import ro.phd.vsp.cpoprocessor.model.SensorData;
import ro.phd.vsp.cpoprocessor.model.logic.SensorDataNtt;

@Component
@RequiredArgsConstructor
@Slf4j
public class ProcessService {

  private final CommunicationService commsService;
  private final DataService dataService;

  public void process(SensorValueDTO sensorValue) {

    SensorDataNtt dataNtt = dtoToNtt(commsService.enrichSensorData(sensorValue.sid()));
    fillValues(sensorValue, dataNtt);

    AlertDTO alert = computeAlert(dataNtt);
    dataService.saveValues(dataNtt);

    if (alert == null) {
      log.info("Alert not needed for sid: {}, val: {}", dataNtt.getSid(), dataNtt.getValue());
      return;
    }

    commsService.sendAlert(alert);
  }

  private AlertDTO computeAlert(SensorDataNtt dataNtt) {
    return needsAlert(dataNtt)
        ? buildAlert(dataNtt)
        : null;
  }

  private AlertDTO buildAlert(SensorDataNtt dataNtt) {
    NotificationDTO notifcation = null;
    CommandDTO command = null;

    if (dataNtt.getCmdConfig().getCmdType().contains(ALERT_NOTIFICATION_TYPE)) {
      notifcation = buildNotification(dataNtt);
    }

    if (dataNtt.getCmdConfig().getCmdType().contains(ALERT_COMMAND_TYPE)) {
      command = buildCommand(dataNtt);
    }

    return new AlertDTO(notifcation, command);
  }

  private NotificationDTO buildNotification(SensorDataNtt dataNtt) {
    return NotificationDTO.builder()
        .notificationType("USER_NOTIF")
        .sendTo("user")
        .message(String.format("NTF for sid: %s, val: %s", dataNtt.getSid(), dataNtt.getValue()))
        .createdOn(LocalDateTime.now())
        .sent(false)
        .retries(0)
        .build();
  }

  private CommandDTO buildCommand(SensorDataNtt dataNtt) {
    return CommandDTO.builder()
        .cmd(dataNtt.getCmdConfig().getCmd())
        .reason("Outlier value")
        .createdOn(LocalDateTime.now())
        .executed(false)
        .retries(0)
        .build();
  }

  private boolean needsAlert(SensorDataNtt dataNtt) {
    return switch (CommandRules.valueOf(dataNtt.getCmdConfig().getCmdRule())) {
      case REFVAL -> isRefValAlert(dataNtt);
      case PERCENTAGE -> isPercentageAlert(dataNtt);
      case STDDEV -> isStdDevAlert(dataNtt);
    };
  }

  private boolean isRefValAlert(SensorDataNtt dataNtt) {

    return dataNtt.getValue().equals(dataNtt.getCmdConfig().getRefValue());
  }

  private boolean isPercentageAlert(SensorDataNtt dataNtt) {
    List<Double> values = dataService.getAllByUpdatedOn(dataNtt.getSid(), 10, ChronoUnit.MINUTES)
        .stream().map(SensorData::getDvalue).toList();

    double mean = computeMean(values);
    double multiplier = dataNtt.getCmdConfig().getMultiplier();

    return dataNtt.getValue() < mean - (mean * multiplier / 100)
        || dataNtt.getValue() > mean + (mean * multiplier / 100);
  }

  private boolean isStdDevAlert(SensorDataNtt dataNtt) {
    List<Double> values = dataService.getAllByUpdatedOn(dataNtt.getSid(), 10, ChronoUnit.MINUTES)
        .stream().map(SensorData::getDvalue).toList();

    double mean = computeMean(values);
    double stddev = computeStandardDeviation(values);
    double multiplier = dataNtt.getCmdConfig().getMultiplier();

    return dataNtt.getValue() < mean - (stddev * multiplier)
        || dataNtt.getValue() > mean + (stddev * multiplier);
  }
}
