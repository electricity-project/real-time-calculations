package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.optimization.OptimizationDTO;
import com.electricity.project.realtimecalculations.api.optimization.OptimizeProductionDTO;
import com.electricity.project.realtimecalculations.core.client.calcdbaccess.CalculationsAccessDbClient;
import com.electricity.project.realtimecalculations.infrastucture.configuration.Threshold;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RealTimeCalcResource {
    private final IRealTimeCalculations realTimeCalculations;
    private final CalculationsAccessDbClient calculationsAccessDbClient;

    @PostMapping("/optimize_production")
    public ResponseEntity<OptimizationDTO> optimizePowerProduction(
            @RequestBody OptimizeProductionDTO optimizeProductionDTO
    ) {
        return ResponseEntity
                .ok(realTimeCalculations.calculateOptimalPowerStationsToRun(optimizeProductionDTO.getPowerProductions(),
                        calculationsAccessDbClient.getFilteredStations(optimizeProductionDTO.getPowerStationFilter())));
    }

    @GetMapping
    public ResponseEntity<Double> setThreshold(@RequestParam Double threshold) {
        Threshold.setEnergyThreshold(threshold);
        return ResponseEntity.ok(Threshold.getEnergyThreshold());
    }

    @GetMapping("/threshold")
    public ResponseEntity<Double> getThreshold() {
        return ResponseEntity.ok(Threshold.getEnergyThreshold());
    }
}
