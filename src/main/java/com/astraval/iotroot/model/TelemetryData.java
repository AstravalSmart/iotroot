package com.astraval.iotroot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "telemetry")
public class TelemetryData {
    @Id
    private String id;

    private String deviceId;
    private String payload;
    private Instant timestamp = Instant.now();

    // getters & setters
}
