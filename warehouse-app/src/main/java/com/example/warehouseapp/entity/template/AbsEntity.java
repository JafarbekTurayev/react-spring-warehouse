package com.example.warehouseapp.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private boolean active = true;

    @Column(updatable = false)
    @CreatedBy
    private UUID createdBy;

    @CreationTimestamp
    private Timestamp createdAt;

    @LastModifiedBy
    private UUID updatedBy;

    //@UpdateTimestamp
    @LastModifiedDate
    private Timestamp updatedAt;

    public AbsEntity(Integer id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }
}
