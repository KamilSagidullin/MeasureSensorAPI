package org.kamil.measuresensorapi.dto;

import jakarta.validation.constraints.*;
import org.kamil.measuresensorapi.models.Sensor;

public class MeasurementDTO {
    @Min(value = -100,message = "Value must be between -100 and 100")
    @Max(value = 100,message = "Value must be between -100 and 100")
    private double value;
    @NotNull(message = "This field must be not empty")
    private Boolean raining;
    @NotNull(message = "This field must not be empty")
    private Sensor sensor;

    public MeasurementDTO() {

    }

    public MeasurementDTO(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

}
