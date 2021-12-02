package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.attachment.Attachment;
import com.example.warehouseapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(value = AuditingEntityListener.class)
public class Product extends AbsEntity {

    @ManyToOne
    private Category category;
    @OneToOne
    private Attachment photo;
    @Column(nullable = false, unique = true)
    private String code;
    @ManyToOne
    private Measurement measurement;



}
