package com.electricity.project.realtimecalculations.core.domains;


import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import com.electricity.project.realtimecalculations.core.client.calcdbaccess.CalculationsAccessDbClient;
import com.electricity.project.realtimecalculations.core.client.centralmodule.CentralClient;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Component
public class PowerProductionEvaluationScheduler {
    private CalculationsAccessDbClient calculationsAccessDbClient;
    private IRealTimeCalculations realTimeCalculations;
    private CentralClient centralClient;

    @Async
    @Scheduled(cron = "1 * * * * ?")
    public void scheduleFixedRateTaskAsync() throws InterruptedException {
        double threshold = 15000.0;
        List<PowerProductionDTO> powerProductionDTOList = Collections.emptyList();
        LocalDateTime timeNow = ZonedDateTime.ofInstant(Instant.now(),ZoneId.of("Europe/Warsaw")).toLocalDateTime().withSecond(0).withNano(0);
        //log.info(timeNow.toString());
        for(int i = 0; i < 10; i++) {
            powerProductionDTOList = calculationsAccessDbClient.getPowerProductionByMinute(timeNow);
            if(!powerProductionDTOList.isEmpty())
                break;
            Thread.sleep(2000);
        }
        log.info(powerProductionDTOList.toString());
        List<String> ipv6Addresses = realTimeCalculations.calculateOptimalPowerStationsToRun(powerProductionDTOList, threshold);
        ipv6Addresses.forEach(ipv6Address-> centralClient.startPowerStation(ipv6Address));
    }
}
