package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.optimization.OptimizationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationFilterDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationState;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import com.electricity.project.realtimecalculations.api.solarpanel.ImmutableSolarPanelDTO;
import com.electricity.project.realtimecalculations.api.windturbine.ImmutableWindTurbineDTO;
import com.electricity.project.realtimecalculations.infrastucture.configuration.Threshold;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class RealTimeCalculations implements IRealTimeCalculations{
    @Override
    public OptimizationDTO calculateOptimalPowerStationsToRun(
            List<PowerProductionDTO> sortedPowerProductionDTOList,
            List<PowerStationDTO> powerStationDTOList) {

        sortedPowerProductionDTOList.sort(Collections.reverseOrder());

        List<List<PowerProductionDTO>> dividedPowerProductionDTOS = sortedPowerProductionDTOList.parallelStream().collect(Collectors.teeing(
                Collectors.filtering(powerProductionDTO -> powerProductionDTO.getIpv6Address().equals(powerStationDTOList.stream()
                        .filter(powerStationDTO -> (powerProductionDTO.getIpv6Address().equals(powerStationDTO.getIpv6Address()) &&
                                powerStationDTO.getClass().equals(ImmutableSolarPanelDTO.class))
                        ).findFirst().map(PowerStationDTO::getIpv6Address).orElse(null)), Collectors.toList()),
                Collectors.filtering(powerProductionDTO -> powerProductionDTO.getIpv6Address().equals(powerStationDTOList.stream()
                        .filter(powerStationDTO -> (powerProductionDTO.getIpv6Address().equals(powerStationDTO.getIpv6Address()) &&
                                powerStationDTO.getClass().equals(ImmutableWindTurbineDTO.class))
                        ).findFirst().map(PowerStationDTO::getIpv6Address).orElse(null)), Collectors.toList()),
                List::of));

        double tmpSum, tmp, runningMax, sum = 0.0;
        double threshold = Threshold.getEnergyThreshold();
        List<String> IpsToTurnOn = new ArrayList<>(), IpsToTurnOff = new ArrayList<>();

        for (List<PowerProductionDTO> powerProductionDTOSubList: dividedPowerProductionDTOS) {
            runningMax = powerProductionDTOSubList.get(0).getProducedPower();
            for (PowerProductionDTO powerProductionDTO: powerProductionDTOSubList) {
                tmp = powerProductionDTO.getProducedPower();
                tmpSum = tmp+sum;
                if(tmp == 0.0) {
                    tmp = runningMax;
                    tmpSum = tmp+sum;
                    if(tmpSum <= threshold){
                        sum += tmp;
                        IpsToTurnOn.add(powerProductionDTO.getIpv6Address());
                    }
                }
                else if(tmpSum <= threshold){
                    sum += tmp;
                }
                else{
                    if(powerProductionDTO.getState() == PowerStationState.WORKING) {
                        IpsToTurnOff.add(powerProductionDTO.getIpv6Address());
                    }
                }
            }
        }

        return OptimizationDTO.builder()
                .addAllIpsToTurnOff(IpsToTurnOff)
                .addAllIpsToTurnOn(IpsToTurnOn)
                .build();
    }
}
