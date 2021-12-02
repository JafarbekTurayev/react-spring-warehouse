package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Measurement;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

    //Add new Meauserement
    public ApiResponse addMeauserement(Measurement measurement) {
        boolean byName = measurementRepository.existsByName(measurement.getName());

        if (byName)
            return new ApiResponse("Given meauserement is already added", false);

        measurementRepository.save(measurement);
        return new ApiResponse("Meauserement successfully addded", true);
    }

    //Get one Meauserement
    public Measurement getOne(Integer id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        return byId.orElseGet(Measurement::new);
    }

    //Get all meauserement
    public List<Measurement> getAll() {
        return measurementRepository.findAll();
    }


    public ApiResponse edit(Measurement dto, Integer id) {
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()){

            Measurement measurement = optionalMeasurement.get();
            measurement.setName(dto.getName());

            measurementRepository.save(measurement);
            return new ApiResponse("The meauserement edited", true);

        }

        return new ApiResponse("The meauserement not found", false);
    }

    public ApiResponse delete(Integer id) {
        Optional<Measurement> byId = measurementRepository.findById(id);
        if (byId.isPresent()){
            Measurement measurement = byId.get();
            measurement.setActive(false);
            measurementRepository.save(measurement);
            return new ApiResponse("Meauserement deactivated", true);
        }
            return new ApiResponse("Meauserement not found" , false);
    }
}
