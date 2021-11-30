package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Input;
import com.example.warehouseapp.entity.InputProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
List<InputProduct> findAllByInput_Id(Integer input_id);
List<InputProduct> findAllByProduct_Id(Integer product_id);



Optional<InputProduct> findByInput_Id(Integer input_id);


}
