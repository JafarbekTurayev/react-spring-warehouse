package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse save(Warehouse warehouse) {
        if (!warehouseRepository.existsByName(warehouse.getName())) {
            warehouseRepository.save(warehouse);
            return new ApiResponse("Saved!", true);
        }
        return new ApiResponse("Bunday ombor bor!", false);
    }
}
