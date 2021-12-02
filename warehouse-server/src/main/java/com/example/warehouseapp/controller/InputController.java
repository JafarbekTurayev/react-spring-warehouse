package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Input;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.responce.InputDTO;
import com.example.warehouseapp.repository.InputRepository;
import com.example.warehouseapp.service.InputService;
import com.example.warehouseapp.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.sql.Timestamp;
import java.text.ParseException;

@RestController
@RequestMapping("api/input")
public class InputController {
    @Autowired
    InputRepository inputRepository;

    @Autowired
    InputService inputService;

    //kirim
    @PostMapping
    public HttpEntity<?> add(@RequestBody InputDTO inputDTO) throws ParseException {
        ApiResponse response = inputService.add(inputDTO);
        return ResponseEntity.ok(response);
    }

    //getAll

    @GetMapping("/all")
    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size) {
        ApiResponse response = inputService.getAll(page, size);
        return ResponseEntity.ok(response);
    }

    //getONE
    @GetMapping("{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse response = inputService.getById(id);
        return ResponseEntity.ok(response);
    }


    //from to
    @GetMapping()
    public HttpEntity<?> getAllFromTO(@RequestParam String from, @RequestParam String to) throws ParseException {
        ApiResponse response = inputService.getAllFromTo(from, to);
        return ResponseEntity.ok(response);
    }

    //kunlik haftalik oylik
    @GetMapping("/searchType") //daily monthly weekly
    public HttpEntity<?> getAllSearchType(@RequestParam String type, @RequestParam String date) throws ParseException {
        ApiResponse response = inputService.getAllSearchType(type, date);
        return ResponseEntity.ok(response);
    }

    //history hamma taminotchi uchun
    @GetMapping("/history") //daily monthly weekly
    public HttpEntity<?> getAllHistoryBySupplier(
            @RequestParam(defaultValue = "0") Integer supplierId,
            @RequestParam(defaultValue = AppConstants.BEGIN_DATE) String from,
            @RequestParam(defaultValue = AppConstants.END_DATE) String to) throws ParseException {
        ApiResponse response = inputService.getAllHistoryType(supplierId, from, to);
        return ResponseEntity.ok(response);
    }


}
