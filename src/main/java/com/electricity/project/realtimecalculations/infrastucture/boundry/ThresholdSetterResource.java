package com.electricity.project.realtimecalculations.infrastucture.boundry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/threshold")
public class ThresholdSetterResource {
    @PostMapping("")
    public ResponseEntity<Void> disconnectPowerStations(@PathVariable Double threshold) {
        // to implement setting env var ENERGY_THRESHOLD
        return ResponseEntity.ok().build();
    }
}
