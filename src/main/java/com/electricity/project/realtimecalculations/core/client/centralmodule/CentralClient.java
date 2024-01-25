package com.electricity.project.realtimecalculations.core.client.centralmodule;

import lombok.NonNull;

public interface CentralClient {
    Void startPowerStation(@NonNull String ipv6Address);

    Void stopPowerStation(@NonNull String ipv6Address);
}
