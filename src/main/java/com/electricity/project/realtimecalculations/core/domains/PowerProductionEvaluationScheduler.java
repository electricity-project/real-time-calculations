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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@EnableAsync
@Slf4j
@Component
public class PowerProductionEvaluationScheduler {
    private final CalculationsAccessDbClient calculationsAccessDbClient;
    private final IRealTimeCalculations realTimeCalculations;
    private final CentralClient centralClient;
    private static PowerStationFilterDTO powerStationFilterDTO;

    public PowerProductionEvaluationScheduler(final CalculationsAccessDbClient calculationsAccessDbClient, final IRealTimeCalculations realTimeCalculations, final CentralClient centralClient) {
        this.calculationsAccessDbClient = calculationsAccessDbClient;
        this.realTimeCalculations = realTimeCalculations;
        this.centralClient = centralClient;
        powerStationFilterDTO = PowerStationFilterDTO.builder()
                .addStatePatterns(PowerStationState.WORKING)
                .addStatePatterns(PowerStationState.STOPPED).build();
    }

    @Async
    @Scheduled(cron = "1 * * * * ?")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        List<PowerProductionDTO> powerProductionDTOList = Collections.emptyList();
        List<PowerStationDTO> powerStationDTOList = Collections.emptyList();
        LocalDateTime timeNow = ZonedDateTime.ofInstant(Instant.now(),ZoneId.of("Europe/Warsaw"))
                .toLocalDateTime().withSecond(0).withNano(0);
        for(int i = 0; i < 10; i++) {
            powerProductionDTOList = calculationsAccessDbClient.getPowerProductionByMinute(timeNow);
            if(!powerProductionDTOList.isEmpty()) {
                powerStationDTOList = calculationsAccessDbClient.getFilteredStations(powerStationFilterDTO);
                break;
            }
            Thread.sleep(2000);
        }
        if(!powerProductionDTOList.isEmpty()) {
            log.info(powerProductionDTOList.toString());
            log.info(powerStationDTOList.toString());
            OptimizationDTO optimizationDTO = realTimeCalculations.calculateOptimalPowerStationsToRun(powerStationDTOList,
                    powerProductionDTOList);
            log.info("To turn on:");
            log.info(optimizationDTO.getIpsToTurnOn().toString());
            log.info("To turn off:");
            log.info(optimizationDTO.getIpsToTurnOff().toString());
            // to verify if routing through central works
//            optimizationDTO.getIpsToTurnOff().forEach(centralClient::stopPowerStation);
//            optimizationDTO.getIpsToTurnOn().forEach(centralClient::stopPowerStation);
        }
        else {
            throw new RuntimeException("No power production data found for " + timeNow);
        }
    }
}
