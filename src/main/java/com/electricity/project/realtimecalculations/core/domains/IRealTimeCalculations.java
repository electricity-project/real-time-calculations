package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.optimization.OptimizationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationDTO;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;

import java.util.List;

public interface IRealTimeCalculations {
    OptimizationDTO calculateOptimalPowerStationsToRun(List<PowerStationDTO> powerStationDTOList, List<PowerProductionDTO> powerProductionDTOList);
}
