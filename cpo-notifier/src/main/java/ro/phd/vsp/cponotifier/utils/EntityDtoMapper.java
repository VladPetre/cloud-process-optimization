package ro.phd.vsp.cponotifier.utils;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ro.phd.vsp.cpocommon.dtos.CommandDTO;
import ro.phd.vsp.cpocommon.dtos.NotificationDTO;
import ro.phd.vsp.cponotifier.model.Command;
import ro.phd.vsp.cponotifier.model.Notification;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EntityDtoMapper {

  public static Notification notificationEntity(NotificationDTO n) {
    return Notification.builder()
        .notificationType(n.notificationType())
        .sendTo(n.sendTo())
        .message(n.message())
        .createdOn(LocalDateTime.now())
        .retries(0)
        .sent(false)
        .build();
  }

  public static Command commandEntity(CommandDTO cmd) {
    return Command.builder()
        .cmd(cmd.cmd())
        .reason(cmd.reason())
        .createdOn(LocalDateTime.now())
        .retries(0)
        .executed(false)
        .build();
  }

  public static NotificationDTO notificationDTO(Notification n) {
    return NotificationDTO.builder()
        .notificationType(n.getNotificationType())
        .sendTo(n.getSendTo())
        .message(n.getMessage())
        .createdOn(n.getCreatedOn())
        .retries(n.getRetries())
        .sent(n.getSent())
        .build();
  }

  public static CommandDTO commandDTO(Command cmd) {
    return CommandDTO.builder()
        .cmd(cmd.getCmd())
        .reason(cmd.getReason())
        .createdOn(cmd.getCreatedOn())
        .retries(cmd.getRetries())
        .executed(cmd.getExecuted())
        .build();
  }

}
