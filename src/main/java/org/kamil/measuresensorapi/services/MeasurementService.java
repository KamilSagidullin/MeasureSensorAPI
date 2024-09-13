package org.kamil.measuresensorapi.services;

import jakarta.transaction.Transactional;
import org.kamil.measuresensorapi.models.Measurement;
import org.kamil.measuresensorapi.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }
    @Transactional
    public int getRainyDaysCount(){
        return findAll().stream().filter(Measurement::isRaining).toList().size();
    }
    @Transactional
    public void save(Measurement measurement) {
       measurement.getSensor().setId(sensorService.findByName(measurement.getSensor().getName()).getId());
       measurement.setCreatedTime(new Date());
        measurementRepository.save(measurement);
    }
}
