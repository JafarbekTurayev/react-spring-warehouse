package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    boolean existsByName(String name);
}
