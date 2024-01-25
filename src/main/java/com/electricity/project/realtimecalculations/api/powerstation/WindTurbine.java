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
public class WindTurbine extends PowerStation {

    private long bladeLength;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WindTurbine that)) return false;
        if (!super.equals(o)) return false;

        return bladeLength == that.bladeLength;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (bladeLength ^ (bladeLength >>> 32));
        return result;
    }
}
