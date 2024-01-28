package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.optimization.OptimizationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationDTO;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import com.electricity.project.realtimecalculations.api.weather.CurrentWeatherDTO;

import java.util.List;

public interface IRealTimeCalculations {
    OptimizationDTO calculateOptimalPowerStationsToRun(List<PowerProductionDTO> powerProductionDTOList, List<PowerStationDTO> powerStationDTOList);
    OptimizationDTO calculateOptimalPowerStationsToRunWithWeather(List<PowerProductionDTO> powerProductionDTOList, List<PowerStationDTO> powerStationDTOList, CurrentWeatherDTO currentWeatherDTO);

}
