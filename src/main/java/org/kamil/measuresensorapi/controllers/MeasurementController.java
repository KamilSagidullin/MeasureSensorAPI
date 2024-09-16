package org.kamil.measuresensorapi.controllers;

import jakarta.validation.Valid;
import org.kamil.measuresensorapi.client.MeasurementResponse;
import org.kamil.measuresensorapi.dto.MeasurementDTO;
import org.kamil.measuresensorapi.models.Measurement;
import org.kamil.measuresensorapi.services.MeasurementService;
import org.kamil.measuresensorapi.util.MeasurementErrorResponse;
import org.kamil.measuresensorapi.util.MeasurementValidator;
import org.kamil.measuresensorapi.util.SensorAlreadyExistsException;
import org.kamil.measuresensorapi.util.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final MeasurementValidator measurementValidator;
    private final ModelMapper mapper;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator, ModelMapper mapper) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
        this.mapper = mapper;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
            }
            throw new SensorNotFoundException(errorMessage.toString());
        }
        measurementService.save(mapper.map(measurementDTO, Measurement.class));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @GetMapping()
    public MeasurementResponse getMeasurements() {
        return new MeasurementResponse(new ArrayList<>(measurementService.findAll()));
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCountJson() {
        return measurementService.getRainyDaysCount();
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(SensorNotFoundException exception) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
//TODO:return DTO