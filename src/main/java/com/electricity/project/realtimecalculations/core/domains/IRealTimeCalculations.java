package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;

import java.util.List;

public interface IRealTimeCalculations {
    List<String> calculateOptimalPowerStationsToRun(List<PowerProductionDTO> powerProductionDTOList, double threshold);
}
