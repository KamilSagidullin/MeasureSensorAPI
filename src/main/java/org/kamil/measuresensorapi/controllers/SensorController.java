package org.kamil.measuresensorapi.controllers;

import jakarta.validation.Valid;
import org.kamil.measuresensorapi.dto.SensorDTO;
import org.kamil.measuresensorapi.models.Sensor;
import org.kamil.measuresensorapi.services.SensorService;
import org.kamil.measuresensorapi.util.SensorAlreadyExistsException;
import org.kamil.measuresensorapi.util.SensorErrorResponse;
import org.kamil.measuresensorapi.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(ModelMapper modelMapper, SensorService sensorService, SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registrate(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO,bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
            }
            throw new SensorAlreadyExistsException(errorMessage.toString());
        }
        sensorService.save(modelMapper.map(sensorDTO, Sensor.class));
        return ResponseEntity.ok(HttpStatus.ACCEPTED);

    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorAlreadyExistsException exception){
        SensorErrorResponse errorResponse = new SensorErrorResponse(exception.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
