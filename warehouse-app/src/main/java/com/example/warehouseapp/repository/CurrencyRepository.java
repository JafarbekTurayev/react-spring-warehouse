package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    boolean existsByName(String name);
    Optional<Currency> findByIdAndActiveTrue(Integer Id);

}
