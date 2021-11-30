package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.responce.CurrencyDto;
import com.example.warehouseapp.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse add(CurrencyDto currencyDto) {
        if (currencyRepository.existsByName(currencyDto.getName())){
            return new ApiResponse("Bunday Currency mavjud!",false);
        }

        Currency currency = new Currency();
        currency.setName(currencyDto.getName());
        currency.setActive(currencyDto.isActive());
        Currency save = currencyRepository.save(currency);
        return new ApiResponse("Saved", true);

    }

    public ApiResponse edit(Integer id, CurrencyDto currencyDto) {
        if (!currencyRepository.existsById(id)) {
            return new ApiResponse("Not Found ", false);
        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        optionalCurrency.get().setName(currencyDto.getName());
        optionalCurrency.get().setActive(currencyDto.isActive());
        Currency save = currencyRepository.save(optionalCurrency.get());
        return new ApiResponse("Edited", true);

    }
}
