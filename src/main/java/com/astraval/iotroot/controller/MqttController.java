package com.astraval.iotroot.controller;

import com.astraval.iotroot.config.MqttConfig;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mqtt")
public class MqttController {
    private final MqttConfig mqttConfig;

    public MqttController(MqttConfig mqttConfig) {
        this.mqttConfig = mqttConfig;
    }

    @PostMapping("/publish")
    public String publish(@RequestParam String topic, @RequestBody String message) {
        mqttConfig.publish(topic, message);
        return "Published to " + topic;
    }
}
