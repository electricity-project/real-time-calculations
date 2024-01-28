package com.electricity.project.realtimecalculations.infrastucture.configuration;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Threshold {
    @Getter
    private static double energyThreshold = 100000.0;

    public static void setEnergyThreshold(double energyThreshold) {
        Threshold.energyThreshold = energyThreshold;
    }
}
