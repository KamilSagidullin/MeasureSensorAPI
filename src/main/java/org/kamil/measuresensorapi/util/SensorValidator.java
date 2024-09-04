package org.kamil.measuresensorapi.util;

import org.kamil.measuresensorapi.models.Sensor;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class SensorValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
