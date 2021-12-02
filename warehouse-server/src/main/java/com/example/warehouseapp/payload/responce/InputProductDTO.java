package com.example.warehouseapp.payload.responce;

import com.example.warehouseapp.entity.Input;
import com.example.warehouseapp.entity.Product;
import lombok.Data;

import java.util.Date;

@Data
public class InputProductDTO {
    private Integer productId;
    private double amount;
    private double price;
    private String expireDate;
}
