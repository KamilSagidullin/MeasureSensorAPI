package org.kamil.measuresensorapi.services;

import jakarta.transaction.Transactional;
import org.kamil.measuresensorapi.models.Sensor;
import org.kamil.measuresensorapi.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    @Transactional
    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }
    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
