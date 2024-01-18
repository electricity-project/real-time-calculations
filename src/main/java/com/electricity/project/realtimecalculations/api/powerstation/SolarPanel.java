package com.electricity.project.realtimecalculations.api.powerstation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class SolarPanel extends PowerStation {

    private double optimalTemperature;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolarPanel that)) return false;
        if (!super.equals(o)) return false;

        return Double.compare(optimalTemperature, that.optimalTemperature) == 0;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(optimalTemperature);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}

