package com.example.warehouseapp.payload;

import com.example.warehouseapp.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResInputProductDTO {

    private String productName;
    private Double amount;
    private Double price;
    private Date expireDate;

}
