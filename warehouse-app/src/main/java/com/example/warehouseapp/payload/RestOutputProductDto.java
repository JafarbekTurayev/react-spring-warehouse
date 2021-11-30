package com.example.warehouseapp.payload;

import com.example.warehouseapp.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestOutputProductDto {
    private String productName;

    private Double amount;

    private Integer price;

}
