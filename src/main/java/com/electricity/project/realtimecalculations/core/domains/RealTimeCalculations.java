package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;


@Slf4j
@Component
public class RealTimeCalculations implements IRealTimeCalculations{
    @Override
    public List<String> calculateOptimalPowerStationsToRun(List<PowerProductionDTO> powerProductionDTOList, double threshold) {
        Collections.sort(powerProductionDTOList);
        log.info(powerProductionDTOList.toString());
        return powerProductionDTOList.stream().map(PowerProductionDTO::getIpv6Address).toList();
    }
}
