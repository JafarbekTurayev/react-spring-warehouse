package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Supplier;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.SupplierDTO;
import com.example.warehouseapp.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupplierServise {
    @Autowired
    SupplierRepository supplierRepository;

    public ApiResponse add(SupplierDTO supplierDTO) {
        Supplier supplier = new Supplier();
        if (!supplierRepository.existsByPhoneNumber(supplierDTO.getName())) {
            supplier.setName(supplierDTO.getName());
            supplier.setPhoneNumber(supplierDTO.getPhoneNumber());
            supplierRepository.save(supplier);
            return new ApiResponse("Saved!", true);
        }
        return new ApiResponse("This supplier already exists", false);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Supplier> onId = supplierRepository.findById(id);
        if (!onId.isPresent()) return new ApiResponse("Not found!", false);

        return new ApiResponse("Mana", true, onId.get());


    }

    public ApiResponse edit(Integer id, Supplier supplier) {
        Optional<Supplier> onId = supplierRepository.findById(id);
        if (!onId.isPresent()) return new ApiResponse("Not found!", false);
        Supplier edit = onId.get();
        edit.setName(supplier.getName());
        edit.setPhoneNumber(supplier.getPhoneNumber());
        supplierRepository.save(edit);
        return new ApiResponse("Edited", true, edit);
    }

}
