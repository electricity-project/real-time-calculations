package com.electricity.project.realtimecalculations.api.powerstationDTO;

import com.electricity.project.realtimecalculations.api.powerstation.PowerStation;
import com.electricity.project.realtimecalculations.api.powerstation.SolarPanel;
import com.electricity.project.realtimecalculations.api.powerstation.WindTurbine;

public enum PowerStationType {
    WIND_TURBINE(WindTurbine.class), SOLAR_PANEL(SolarPanel.class);

    public final Class<? extends PowerStation> powerStationClass;

    PowerStationType(Class<? extends PowerStation> powerStationClass) {
        this.powerStationClass = powerStationClass;
    }
}
