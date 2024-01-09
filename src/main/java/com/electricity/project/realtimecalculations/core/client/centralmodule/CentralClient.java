package com.electricity.project.realtimecalculations.core.client.centralmodule;

import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public interface CentralClient {
    Void startPowerStation(@NonNull String ipv6Address);
    Void stopPowerStation(@NonNull String ipv6Address);
}
