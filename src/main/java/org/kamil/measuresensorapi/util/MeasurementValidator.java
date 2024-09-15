package org.kamil.measuresensorapi.util;

import org.kamil.measuresensorapi.dto.MeasurementDTO;
import org.kamil.measuresensorapi.models.Measurement;
import org.kamil.measuresensorapi.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService service;

    @Autowired
    public MeasurementValidator(SensorService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if (service.findByName(measurementDTO.getSensor().getName()).isEmpty()){
            errors.rejectValue("sensor","","Measurement sensor must be registered");
        }
        if (measurementDTO.getValue() > 99 || measurementDTO.getValue() < -99){
            errors.rejectValue("value","","Value must be between -100 and 100");
        }
    }
}
