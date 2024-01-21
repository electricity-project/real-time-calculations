package com.electricity.project.realtimecalculations.api.optimization;

import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationFilterDTO;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutableOptimizeProductionDTO.class)
@JsonDeserialize(as = ImmutableOptimizeProductionDTO.class)
public interface OptimizeProductionDTO {

    @JsonProperty(value = "powerProductions", required = true)
    List<PowerProductionDTO> getPowerProductions();

    @JsonProperty(value = "powerStationFilter", required = true)
    PowerStationFilterDTO getPowerStationFilter();
}
