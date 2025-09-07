package com.astraval.iotroot.service;

import com.astraval.iotroot.model.TelemetryData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
interface TelemetryRepository extends MongoRepository<TelemetryData, String> {}

@Service
public class TelemetryService {
    private final TelemetryRepository repo;

    public TelemetryService(TelemetryRepository repo) {
        this.repo = repo;
    }

    public TelemetryData save(TelemetryData data) {
        return repo.save(data);
    }

    public List<TelemetryData> getAll() {
        return repo.findAll();
    }
}
