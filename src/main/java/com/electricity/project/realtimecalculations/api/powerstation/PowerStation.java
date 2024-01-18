package com.electricity.project.realtimecalculations.api.powerstation;

import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class PowerStation {

    private Long id;

    private String ipv6Address;

    private PowerStationState state;

    private LocalDateTime creationTime;

    private double maxPower;

    private boolean isConnected;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerStation that)) return false;

        if (Double.compare(maxPower, that.maxPower) != 0) return false;
        if (!ipv6Address.equals(that.ipv6Address)) return false;
        return creationTime.equals(that.creationTime);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ipv6Address.hashCode();
        result = 31 * result + creationTime.hashCode();
        temp = Double.doubleToLongBits(maxPower);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
