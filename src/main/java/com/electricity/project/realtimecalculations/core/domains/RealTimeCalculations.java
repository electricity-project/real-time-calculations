package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.optimization.OptimizationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationState;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import com.electricity.project.realtimecalculations.api.solarpanel.ImmutableSolarPanelDTO;
import com.electricity.project.realtimecalculations.api.weather.CurrentWeatherDTO;
import com.electricity.project.realtimecalculations.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.realtimecalculations.infrastucture.configuration.Threshold;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Slf4j
@Component
public class RealTimeCalculations implements IRealTimeCalculations {

    public OptimizationDTO calculateOptimalPowerStationsToRunWithWeather(
            List<PowerProductionDTO> sortedPowerProductionDTOList,
            List<PowerStationDTO> powerStationDTOList,
            CurrentWeatherDTO currentWeatherDTO
    ) {
        sortedPowerProductionDTOList = sortedPowerProductionDTOList.stream()
                .sorted(Collections.reverseOrder())
                .toList();

        List<String> ipsToTurnOn = new ArrayList<>(), ipsToTurnOff = new ArrayList<>();

        List<List<PowerProductionDTO>> dividedPowerProductionDTOS = sortedPowerProductionDTOList.stream()
                .collect(Collectors.teeing(
                        filterPowerStation(powerStationDTOList, ImmutableSolarPanelDTO.class),
                        filterWindTurbine(powerStationDTOList, ipsToTurnOff),
                        List::of));

        double tmpSum, tmp, runningMax, sum = 0.0;
        double threshold = Threshold.getEnergyThreshold();

        for (int i = 0; i < dividedPowerProductionDTOS.size(); i++) {
            List<PowerProductionDTO> powerProductionDTOSubList = dividedPowerProductionDTOS.get(i);
            if (powerProductionDTOSubList.isEmpty()) continue;

            runningMax = powerProductionDTOSubList.get(0).getProducedPower();
            for (PowerProductionDTO powerProductionDTO : powerProductionDTOSubList) {
                tmp = powerProductionDTO.getProducedPower();
                tmpSum = tmp + sum;
                if (tmp == 0.0 && powerProductionDTO.getState().equals(PowerStationState.STOPPED)) {
                    if (i == 1 && currentWeatherDTO.getMpSWindSpeed() <= 5.0) {
                        continue;
                    }
                    tmp = runningMax;
                    tmpSum = tmp + sum;
                    if (tmpSum <= threshold) {
                        sum += tmp;
                        ipsToTurnOn.add(powerProductionDTO.getIpv6Address());
                    }
                } else if (tmpSum <= threshold) {
                    sum += tmp;
                } else {
                    if (powerProductionDTO.getState() == PowerStationState.WORKING) {
                        ipsToTurnOff.add(powerProductionDTO.getIpv6Address());
                    }
                }
            }
        }

        return OptimizationDTO.builder()
                .addAllIpsToTurnOff(ipsToTurnOff)
                .addAllIpsToTurnOn(ipsToTurnOn)
                .build();

    }

    @Override
    public OptimizationDTO calculateOptimalPowerStationsToRun(
            List<PowerProductionDTO> sortedPowerProductionDTOList,
            List<PowerStationDTO> powerStationDTOList) {

        sortedPowerProductionDTOList = sortedPowerProductionDTOList.stream()
                .sorted(Collections.reverseOrder())
                .toList();

        List<String> ipsToTurnOn = new ArrayList<>(), ipsToTurnOff = new ArrayList<>();
        List<List<PowerProductionDTO>> dividedPowerProductionDTOS = sortedPowerProductionDTOList.stream()
                .collect(Collectors.teeing(
                        filterPowerStation(powerStationDTOList, ImmutableSolarPanelDTO.class),
                        filterPowerStation(powerStationDTOList, ImmutableWindTurbineDTO.class),
                        List::of));

        double tmpSum, tmp, runningMax, sum = 0.0;
        double threshold = Threshold.getEnergyThreshold();

        for (List<PowerProductionDTO> powerProductionDTOSubList : dividedPowerProductionDTOS) {
            if (powerProductionDTOSubList.isEmpty()) continue;

            runningMax = powerProductionDTOSubList.get(0).getProducedPower();
            for (PowerProductionDTO powerProductionDTO : powerProductionDTOSubList) {
                tmp = powerProductionDTO.getProducedPower();
                tmpSum = tmp + sum;
                if (tmp == 0.0) {
                    tmp = runningMax;
                    tmpSum = tmp + sum;
                    if (tmpSum <= threshold) {
                        sum += tmp;
                        ipsToTurnOn.add(powerProductionDTO.getIpv6Address());
                    }
                } else if (tmpSum <= threshold) {
                    sum += tmp;
                } else {
                    if (powerProductionDTO.getState() == PowerStationState.WORKING) {
                        ipsToTurnOff.add(powerProductionDTO.getIpv6Address());
                    }
                }
            }
        }

        return OptimizationDTO.builder()
                .addAllIpsToTurnOff(ipsToTurnOff)
                .addAllIpsToTurnOn(ipsToTurnOn)
                .build();
    }

    private static Collector<PowerProductionDTO, ?, List<PowerProductionDTO>> filterWindTurbine(
            List<PowerStationDTO> powerStationDTOList, List<String> ipsToTurnOff) {
        return Collectors.filtering(powerProductionDTO -> powerProductionDTO.getIpv6Address().equals(powerStationDTOList.stream()
                .filter(powerStationDTO -> {
                    boolean checkIfWindTurbine = powerProductionDTO.getIpv6Address().equals(powerStationDTO.getIpv6Address()) && powerStationDTO.getClass().equals(ImmutableWindTurbineDTO.class);

                    if (checkIfWindTurbine && powerStationDTO.getState().equals(PowerStationState.WORKING) && powerProductionDTO.getProducedPower().equals(0L)) {
                        ipsToTurnOff.add(powerStationDTO.getIpv6Address());
                        return false;
                    }
                    return checkIfWindTurbine;
                }).findFirst()
                .map(PowerStationDTO::getIpv6Address).orElse(null)), Collectors.toList());
    }

    private static Collector<PowerProductionDTO, ?, List<PowerProductionDTO>> filterPowerStation(
            List<PowerStationDTO> powerStationDTOList, Class<?> powerStationType) {
        return Collectors.filtering(powerProductionDTO -> powerProductionDTO.getIpv6Address().equals(powerStationDTOList.stream()
                .filter(powerStationDTO -> powerProductionDTO.getIpv6Address().equals(powerStationDTO.getIpv6Address()) && powerStationDTO.getClass().equals(powerStationType))
                .findFirst()
                .map(PowerStationDTO::getIpv6Address).orElse(null)), Collectors.toList());
    }
}
