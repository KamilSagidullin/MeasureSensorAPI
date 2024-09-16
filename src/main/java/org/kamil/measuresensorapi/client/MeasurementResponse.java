package org.kamil.measuresensorapi.client;

import org.kamil.measuresensorapi.models.Measurement;

import java.util.ArrayList;
import java.util.List;

public class MeasurementResponse {
    List<Measurement> measurements;
    public MeasurementResponse(){}
    public MeasurementResponse(ArrayList<Measurement> es) {
        this.measurements = es;
    }


    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
