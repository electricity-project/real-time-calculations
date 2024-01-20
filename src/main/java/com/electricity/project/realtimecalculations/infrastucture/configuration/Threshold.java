package com.electricity.project.realtimecalculations.infrastucture.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
public class Threshold{
    @Getter
    private static double energyThreshold = 1000000.0;
    public static void setEnergyThreshold(double energyThreshold) {
        Threshold.energyThreshold = energyThreshold;
    }
}
