package com.electricity.project.realtimecalculations.api.optimization;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutableOptimizationDTO.class)
@JsonDeserialize(as = ImmutableOptimizationDTO.class)
public interface OptimizationDTO {
    static ImmutableOptimizationDTO.Builder builder() {
        return ImmutableOptimizationDTO.builder();
    }

    @JsonProperty(value = "IpsToTurnOff")
    List<String> getIpsToTurnOff();

    @JsonProperty(value = "IpsToTurnOn")
    List<String> getIpsToTurnOn();

}
