package com.example.warehouseapp.controller;


import com.example.warehouseapp.entity.User;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.UserDto;
import com.example.warehouseapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAuthority('ADD_USER')")
    @PostMapping()
    public HttpEntity<?> addUser(@RequestBody UserDto userDto){
        ApiResponse response = userService.saveUser(userDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PreAuthorize(value = "hasAuthority('READ_USER')")
    @GetMapping("/all")
    public HttpEntity<?> getAll(){
        List<User> all = userService.getALL();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id){
        User user = userService.getOneById(id);
        return ResponseEntity.status(user!=null ? 201: 409).body(user);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_USER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editUser(@PathVariable UUID id, @RequestBody User user){
        User edited = userService.editUser(id, user);
        return ResponseEntity.status(edited!=null? 201: 409).body(edited);
    }

    @PreAuthorize(value = "hasAuthority(' DELETE_USER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteUser(@PathVariable UUID id){
    ApiResponse apiResponse = userService.delete(id);
    return ResponseEntity.status(apiResponse.isSuccess() ? 201: 409).body(apiResponse);
    }

}
