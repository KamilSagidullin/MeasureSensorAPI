package org.kamil.measuresensorapi.services;

import jakarta.transaction.Transactional;
import org.kamil.measuresensorapi.models.Measurement;
import org.kamil.measuresensorapi.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Transactional
    public void save(Measurement measurement) {
       measurement.getSensor().setId(sensorService.findByName(measurement.getSensor().getName()).getId());
       measurement.setCreatedTime(new Date());
        measurementRepository.save(measurement);
    }
}
