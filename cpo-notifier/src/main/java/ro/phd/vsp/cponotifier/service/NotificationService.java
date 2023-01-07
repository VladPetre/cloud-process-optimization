package ro.phd.vsp.cponotifier.service;

import static ro.phd.vsp.cponotifier.utils.EntityDtoMapper.notificationDTO;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.phd.vsp.cpocommon.dtos.AlertDTO;
import ro.phd.vsp.cpocommon.dtos.CommandDTO;
import ro.phd.vsp.cpocommon.dtos.NotificationDTO;
import ro.phd.vsp.cponotifier.model.Command;
import ro.phd.vsp.cponotifier.model.Notification;
import ro.phd.vsp.cponotifier.repository.CommandsRepository;
import ro.phd.vsp.cponotifier.repository.NotificationsRepository;
import ro.phd.vsp.cponotifier.utils.EntityDtoMapper;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

  private static final int MAX_RETRIES = 3;
  private final NotificationsRepository notificationsRepository;
  private final CommandsRepository commandsRepository;
  private final Random random = new Random();

  public AlertDTO processNotification(AlertDTO alertDTO) {

    //TODO: implement priority of notifications/commands
    return new AlertDTO(sendNotification(alertDTO), executeCmd(alertDTO));
  }

  private NotificationDTO sendNotification(AlertDTO alertDTO) {

    if (alertDTO.notification() != null) {
      return null;
    }

    Notification notification = EntityDtoMapper.notificationEntity(alertDTO.notification());
    notification = notificationsRepository.save(notification);

    do {
      try {
        notification.setSent(sendNotification(notification));
        notification = notificationsRepository.save(notification);
        log.info("Notification sent: {}", notification.getId());
      } catch (InterruptedException e) {
        notification.setRetries(notification.getRetries() + 1);
      }
    }
    while (!notification.getSent() && notification.getRetries() <= MAX_RETRIES);

    if (!notification.getSent()) {
      log.error("Could not send notification: {}", notification.getId());
    }

    return notificationDTO(notification);
  }


  private CommandDTO executeCmd(AlertDTO alertDTO) {

    if (alertDTO.command() != null) {
      return null;
    }

    Command cmd = EntityDtoMapper.commandEntity(alertDTO.command());
    cmd = commandsRepository.save(cmd);

    do {
      try {
        cmd.setExecuted(executeCommand(cmd));
        cmd = commandsRepository.save(cmd);
        log.info("Cmd executed: {}", cmd.getId());
      } catch (InterruptedException e) {
        cmd.setRetries(cmd.getRetries() + 1);
      }
    } while (!cmd.getExecuted() && cmd.getRetries() <= MAX_RETRIES);

    if (!cmd.getExecuted()) {
      log.error("Could not execute command: {}", cmd.getId());
    }

    return EntityDtoMapper.commandDTO(cmd);
  }

  private boolean sendNotification(Notification n) throws InterruptedException {
    Thread.sleep(getRandomNumber(0, 25));
    return true;
  }

  private boolean executeCommand(Command c) throws InterruptedException {
    Thread.sleep(getRandomNumber(0, 25));
    return true;
  }

  public int getRandomNumber(int min, int max) {
    return ((random.nextInt() * (max - min)) + min);
  }
}
