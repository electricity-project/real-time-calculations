package com.electricity.project.realtimecalculations.api.production;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.NonNull;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Optional;

@Value.Immutable
@Value.Style
@JsonSerialize(as = ImmutablePowerProductionDTO.class)
@JsonDeserialize(as = ImmutablePowerProductionDTO.class)
public interface PowerProductionDTO extends Comparable<PowerProductionDTO> {

    @JsonProperty(value = "id")
    Optional<Long> getId();

    @JsonProperty(value = "ipv6Address", required = true)
    String getIpv6Address();

    @JsonProperty(value = "state", required = true)
    PowerStationState getState();

    @JsonProperty(value = "power", required = true)
    Long getProducedPower();

    @JsonProperty(value = "timestamp", required = true)
    LocalDateTime getTimestamp();

    @Override
    default int compareTo(@NonNull PowerProductionDTO powerProductionDTO) {
        if (getProducedPower() == null || powerProductionDTO.getProducedPower() == null) {
            return 0;
        }
        return getProducedPower().compareTo(powerProductionDTO.getProducedPower());
    }
}
