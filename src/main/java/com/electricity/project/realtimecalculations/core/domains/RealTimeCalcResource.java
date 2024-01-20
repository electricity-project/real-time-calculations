package com.electricity.project.realtimecalculations.core.domains;

import com.electricity.project.realtimecalculations.api.optimization.OptimizationDTO;
import com.electricity.project.realtimecalculations.api.powerstationDTO.PowerStationFilterDTO;
import com.electricity.project.realtimecalculations.api.production.PowerProductionDTO;
import com.electricity.project.realtimecalculations.core.client.calcdbaccess.CalculationsAccessDbClient;
import com.electricity.project.realtimecalculations.infrastucture.configuration.Threshold;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RealTimeCalcResource {
    private final IRealTimeCalculations realTimeCalculations;
    private final CalculationsAccessDbClient calculationsAccessDbClient;

    @PostMapping("/optimize_production")
    public ResponseEntity<OptimizationDTO> getPowerStations(
            @RequestBody List<PowerProductionDTO> powerProductionDTO,
            @RequestBody PowerStationFilterDTO powerStationFilterDTO
    ) {
        return ResponseEntity
                .ok(realTimeCalculations.calculateOptimalPowerStationsToRun(powerProductionDTO,
                        calculationsAccessDbClient.getFilteredStations(powerStationFilterDTO)));
    }

    @GetMapping("")
    public ResponseEntity<Double> setThreshold(@RequestParam Double threshold) {
        Threshold.setEnergyThreshold(threshold);
        return ResponseEntity.ok(Threshold.getEnergyThreshold());
    }
    @GetMapping("/threshold")
    public ResponseEntity<Double> getThreshold() {
        return ResponseEntity.ok(Threshold.getEnergyThreshold());
    }
}
