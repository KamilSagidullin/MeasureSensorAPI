package org.kamil.measuresensorapi.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    private double value;
    @Column(name = "raining")
    private boolean raining;

    @Column(name = "created_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdTime;
    @ManyToOne()
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;

    public Measurement() {

    }

    public Measurement(int id, double value, boolean raining,  Date createdTime) {
        this.id = id;
        this.value = value;
        this.raining = raining;
        this.createdTime = createdTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
