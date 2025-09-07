package com.astraval.iotroot.controller;

import com.astraval.iotroot.model.TelemetryData;
import com.astraval.iotroot.service.TelemetryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    /**
     * Save telemetry data into MongoDB
     * Example POST body:
     * {
     *   "deviceId": "sensor-1",
     *   "payload": "25.5"
     * }
     */
    @PostMapping
    public TelemetryData saveTelemetry(@RequestBody TelemetryData data) {
        return telemetryService.save(data);
    }

    /**
     * Get all telemetry records from MongoDB
     */
    @GetMapping
    public List<TelemetryData> getAllTelemetry() {
        return telemetryService.getAll();
    }
}
