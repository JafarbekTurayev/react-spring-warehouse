package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Output;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.responce.OutputDto;
import com.example.warehouseapp.repository.OutputRepository;
import com.example.warehouseapp.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/output")
public class OutputController {
    @Autowired
    OutputService outputService;
    @Autowired
    OutputRepository outputRepository;

    @PostMapping
    public HttpEntity<?> addOutput(@RequestBody OutputDto outputDto) {
        ApiResponse response = outputService.addOutput(outputDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/all")
    public HttpEntity<?> getAll() {
        List<Output> outputs = outputRepository.findAll();
        return ResponseEntity.ok(outputs);
    }

    @GetMapping("{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse responseOne = outputService.getById(id);
        return ResponseEntity.ok(responseOne);
    }

    @PutMapping("/edit")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody OutputDto outputDto) {
        ApiResponse responseEdit = outputService.aditById(id, outputDto);
        return ResponseEntity.ok(responseEdit);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> removeId(@PathVariable Integer id) {
        if (!outputRepository.existsById(id)) {
            return ResponseEntity.ok("Not Found");
        }
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return ResponseEntity.ok("Deleted");
    }


    //oy kiritilsa sotilmagan mahsulotlar
    //oylik history
    //shu paytgacha top 10talik
    //aborot summasi oylik kunlik yillik chiqimlar degani tushum ombor uchun

    //getAll
    @GetMapping("/allRes")
    public HttpEntity<?> getAll2(@RequestParam int page,@RequestParam int size){
        ApiResponse response = outputService.getAll(page,size);
        return ResponseEntity.ok(response);
    }

    //from to
    @GetMapping ("/from")
    public HttpEntity<?> getAllFromTo(@RequestParam String from,@RequestParam String to) throws ParseException {
        ApiResponse response = outputService.getAllFromTo(from,to);
        return ResponseEntity.ok(response);
    }

    //kun hafta oy
    @GetMapping ("/searchType")
    public HttpEntity<?> getAllSearchType(@RequestParam String type, @RequestParam String date ) throws ParseException {
        ApiResponse response = outputService.getAllSearchType(type,date);
        return ResponseEntity.ok(response);
    }

}
