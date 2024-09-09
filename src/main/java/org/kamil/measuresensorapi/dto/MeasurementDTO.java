package org.kamil.measuresensorapi.dto;

import org.kamil.measuresensorapi.models.Sensor;

public class MeasurementDTO {
    private double value;
    private boolean raining;
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
