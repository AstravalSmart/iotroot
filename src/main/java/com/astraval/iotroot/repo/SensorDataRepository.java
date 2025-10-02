package com.astraval.iotroot.repo;

import com.astraval.iotroot.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SensorDataRepository extends MongoRepository<SensorData, String> {
    List<SensorData> findByUserIdOrderByTimestampDesc(String userId);
}