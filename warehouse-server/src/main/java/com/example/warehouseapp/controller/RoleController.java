package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Role;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.responce.InputDTO;
import com.example.warehouseapp.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    RolesService rolesService;

    @PostMapping("add")
    public HttpEntity<?> add(@RequestBody Role role) throws ParseException {
        ApiResponse response = rolesService.add(role);
        return ResponseEntity.ok(response);
    }


    @GetMapping
    public HttpEntity<?> getAll(@RequestParam int page, @RequestParam int size){
        ApiResponse apiResponse = rolesService.getAll(page, size);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Role one = rolesService.getOne(id);
        return ResponseEntity.ok(one);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody Role dto){
        ApiResponse edit = rolesService.edit(id, dto);
        return ResponseEntity.status(edit.isSuccess()? 201 : 409).body(edit);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete = rolesService.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 204 : 409).body(delete);
    }

    //edit
    //getONe
    //getAll
    //Akmal
}
