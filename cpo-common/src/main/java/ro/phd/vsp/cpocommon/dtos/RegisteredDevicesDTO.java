package ro.phd.vsp.cpocommon.dtos;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public record RegisteredDevicesDTO(Map<UUID, Set<UUID>> registeredDevices) {}
