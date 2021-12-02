package com.example.warehouseapp.payload;

import com.example.warehouseapp.entity.InputProduct;
import com.example.warehouseapp.entity.Supplier;
import com.example.warehouseapp.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResInputDTO {

    private Date date;
    private String warehouseName;
    private String supplierName;
    private String currencyName;
    private String factureNumber;
    List<ResInputProductDTO> inputProductList;
    private double summa;

}
