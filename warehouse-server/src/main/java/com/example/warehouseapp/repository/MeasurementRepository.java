package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement,Integer> {
    boolean existsByName(String name);
}
