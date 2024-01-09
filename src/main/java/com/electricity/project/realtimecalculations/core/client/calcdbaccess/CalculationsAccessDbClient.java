package com.electricity.project.realtimecalculations.core.client.calcdbaccess;

import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public interface CalculationsAccessDbClient {
    List<PowerProductionDTO> getPowerProductionByMinute(@NonNull LocalDateTime time);
}
