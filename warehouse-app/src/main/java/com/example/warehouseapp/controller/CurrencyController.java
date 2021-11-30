package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.responce.CurrencyDto;
import com.example.warehouseapp.repository.CurrencyRepository;
import com.example.warehouseapp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;
    @Autowired
    CurrencyRepository currencyRepository;

    @PostMapping
    public HttpEntity<?> addCurrency(@RequestBody CurrencyDto currencyDto) {

        ApiResponse responseCurrency = currencyService.add(currencyDto);
        return ResponseEntity.ok(responseCurrency);
    }

    @GetMapping("{name}")
    public HttpEntity<?> getName(@PathVariable String name) {
        boolean nameCurrency = currencyRepository.existsByName(name);
        if (!nameCurrency) {
            return ResponseEntity.ok("Not Found");
        }
        return ResponseEntity.ok((name));
    }

    @GetMapping("/all")
    public HttpEntity<?> getAll() {
        List<Currency> currencies = currencyRepository.findAll();
        return ResponseEntity.ok(currencies);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> remove(@PathVariable Integer id) {
        Optional<Currency> byIdAndActiveTrue = currencyRepository.findByIdAndActiveTrue(id);
        if (byIdAndActiveTrue.isPresent()) {
            Currency currency = byIdAndActiveTrue.get();
            currency.setActive(false);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.ok("Bunday Id mavjud emas" );
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editCurrency(@PathVariable Integer id, @RequestBody CurrencyDto currencyDto) {
        ApiResponse responseEdit = currencyService.edit(id, currencyDto);
        return ResponseEntity.status(responseEdit.isSuccess() ? 200 : 404).body(responseEdit);
    }
}
