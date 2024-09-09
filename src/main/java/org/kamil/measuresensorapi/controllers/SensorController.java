package org.kamil.measuresensorapi.controllers;

import org.kamil.measuresensorapi.dto.SensorDTO;
import org.kamil.measuresensorapi.models.Sensor;
import org.kamil.measuresensorapi.services.SensorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrate(@RequestBody SensorDTO sensorDTO) {
        sensorService.save(modelMapper.map(sensorDTO,Sensor.class));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }
}
