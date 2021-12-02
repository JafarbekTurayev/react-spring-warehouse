package com.example.warehouseapp.payload.responce;

import com.example.warehouseapp.entity.Currency;
import com.example.warehouseapp.entity.Warehouse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InputDTO {
    //    private String date;
    private Integer warehouseId;
    private Integer currencyId;
    private String factureName;
    private Integer supplierId;

    private List<InputProductDTO> inputProductDTOS;
}
