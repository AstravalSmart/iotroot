package com.astraval.iotroot.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.clientId}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    private MqttClient mqttClient;

    @PostConstruct
    public void connect() {
        try {
            mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            mqttClient.connect(options);

            System.out.println("✅ Connected to broker: " + broker);

            mqttClient.subscribe(topic, (t, message) -> {
                String payload = new String(message.getPayload());
                System.out.println("📩 Received [" + t + "] " + payload);
                // TODO: forward to TelemetryService
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String payload) {
        try {
            mqttClient.publish(topic, new MqttMessage(payload.getBytes()));
            System.out.println("📤 Published [" + topic + "] " + payload);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void disconnect() {
        try {
            if (mqttClient != null && mqttClient.isConnected()) {
                mqttClient.disconnect();
                System.out.println("❌ Disconnected from broker");
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
