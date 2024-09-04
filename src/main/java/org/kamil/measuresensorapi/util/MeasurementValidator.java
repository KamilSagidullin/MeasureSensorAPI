package org.kamil.measuresensorapi.util;

import org.kamil.measuresensorapi.models.Measurement;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class MeasurementValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
