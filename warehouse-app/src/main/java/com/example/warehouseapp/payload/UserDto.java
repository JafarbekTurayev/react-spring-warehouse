package com.example.warehouseapp.payload;

import lombok.Data;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private Integer roleId;

}
