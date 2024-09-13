package org.kamil.measuresensorapi.controllers;

import org.kamil.measuresensorapi.dto.MeasurementDTO;
import org.kamil.measuresensorapi.models.Measurement;
import org.kamil.measuresensorapi.services.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper mapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper mapper) {
        this.measurementService = measurementService;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody MeasurementDTO measurementDTO) {
        measurementService.save(mapper.map(measurementDTO, Measurement.class));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }
    @GetMapping()
    public List<Measurement> getMeasurements(){
        return measurementService.findAll();
    }
    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCountJson(){
        return measurementService.getRainyDaysCount();
    }
}
