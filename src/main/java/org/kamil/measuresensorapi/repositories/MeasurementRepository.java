package org.kamil.measuresensorapi.repositories;

import org.kamil.measuresensorapi.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
}
