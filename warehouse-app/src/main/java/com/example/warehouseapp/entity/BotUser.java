package com.example.warehouseapp.entity;

import com.example.warehouseapp.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BotUser extends AbsEntity {

    private String chatId;
    private String state;
    private String lang;
    private Double lat;
    private Double lon;
    private String address;

}
