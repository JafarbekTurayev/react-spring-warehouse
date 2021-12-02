package com.example.warehouseapp.payload;

import com.example.warehouseapp.entity.Supplier;
import com.example.warehouseapp.service.SupplierServise;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;


}

