package com.example.warehouseapp.payload;

import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.entity.OutputProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResOutputDto {

    private Date date;

    private String warehouseName;

    private String currencyName;

    private String factureNumber;

    List<RestOutputProductDto> outputProductList;




}
