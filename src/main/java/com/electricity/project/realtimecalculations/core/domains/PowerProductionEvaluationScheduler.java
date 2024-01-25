package com.electricity.project.realtimecalculations.core.domains;


import com.electricity.project.realtimecalculations.api.optimization.OptimizationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationFilterDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationState;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import com.electricity.project.realtimecalculations.core.client.calcdbaccess.CalculationsAccessDbClient;
import com.electricity.project.realtimecalculations.core.client.centralmodule.CentralClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@EnableAsync
@Slf4j
@Component
public class PowerProductionEvaluationScheduler {
    private final CalculationsAccessDbClient calculationsAccessDbClient;
    private final IRealTimeCalculations realTimeCalculations;
    private final CentralClient centralClient;
    private static final PowerStationFilterDTO powerStationFilterDTO;

    static {
        powerStationFilterDTO = PowerStationFilterDTO.builder()
                .addStatePatterns(PowerStationState.WORKING)
                .addStatePatterns(PowerStationState.STOPPED).build();
    }

    public PowerProductionEvaluationScheduler(final CalculationsAccessDbClient calculationsAccessDbClient, final IRealTimeCalculations realTimeCalculations, final CentralClient centralClient) {
        this.calculationsAccessDbClient = calculationsAccessDbClient;
        this.realTimeCalculations = realTimeCalculations;
        this.centralClient = centralClient;
    }

    @Async
    @Scheduled(cron = "15 * * * * ?")
    public void scheduleFixedRateTaskAsync() {
        ZonedDateTime timeNow = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("Europe/Warsaw"))
                .withSecond(0).withNano(0);

        List<PowerProductionDTO> powerProductionDTOList = calculationsAccessDbClient.getPowerProductionByMinute(timeNow);
        if (!powerProductionDTOList.isEmpty()) {
            List<PowerStationDTO> powerStationDTOList = calculationsAccessDbClient.getFilteredStations(powerStationFilterDTO);
            OptimizationDTO optimizationDTO = realTimeCalculations.calculateOptimalPowerStationsToRun(powerProductionDTOList, powerStationDTOList);
            optimizationDTO.getIpsToTurnOff().forEach(centralClient::stopPowerStation);
            optimizationDTO.getIpsToTurnOn().forEach(centralClient::startPowerStation);
        } else {
            throw new RuntimeException("No power production data found for " + timeNow);
        }
    }
}
