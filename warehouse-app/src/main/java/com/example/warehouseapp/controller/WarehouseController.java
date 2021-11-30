package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PreAuthorize(value = "hasAuthority('ADD_WAREHOUSE')")
    @PostMapping
    public HttpEntity<?> addWarehouse(@RequestBody Warehouse warehouse) {
        ApiResponse response = warehouseService.save(warehouse);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    //edit
    //one
    //all
    //delete active false
    //Abdumalik
}