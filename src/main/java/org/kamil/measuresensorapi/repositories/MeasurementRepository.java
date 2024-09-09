package org.kamil.measuresensorapi.repositories;

import org.kamil.measuresensorapi.models.Measurement;
import org.kamil.measuresensorapi.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    Measurement findBySensor(Sensor sensor);
}
