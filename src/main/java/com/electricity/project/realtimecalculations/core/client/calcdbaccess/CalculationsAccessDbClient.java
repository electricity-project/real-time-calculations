package com.electricity.project.realtimecalculations.core.client.calcdbaccess;

import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationFilterDTO;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import lombok.NonNull;

import java.time.ZonedDateTime;
import java.util.List;

public interface CalculationsAccessDbClient {
    List<PowerProductionDTO> getPowerProductionByMinute(@NonNull ZonedDateTime time);

    List<PowerStationDTO> getFilteredStations(@NonNull PowerStationFilterDTO powerStationFilterDTO);
}
