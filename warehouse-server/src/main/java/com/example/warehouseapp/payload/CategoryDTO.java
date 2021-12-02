package com.example.warehouseapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
//    private Integer id ;
    private String name;
    private Integer parentCategoryId;
    private boolean active=true;
//    private UUID createdBy;
//    private Timestamp createdAt;
//    private UUID updatedBy;
//    private Timestamp updatedAt;

}
