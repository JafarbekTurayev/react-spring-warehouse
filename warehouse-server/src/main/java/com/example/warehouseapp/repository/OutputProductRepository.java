package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.OutputProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {
    List<OutputProduct> findAllByOutput_Id(Integer output_id);
    List<OutputProduct> findAllByProduct_Id(Integer product_id);

    Optional<OutputProduct> findByOutput_Id(Integer output_id);
}
