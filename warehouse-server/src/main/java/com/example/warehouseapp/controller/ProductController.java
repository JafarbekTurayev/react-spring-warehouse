package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Product;
import com.example.warehouseapp.entity.Warehouse;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.ProductDTO;
import com.example.warehouseapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
        ApiResponse response = productService.saveProduct(productDTO);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/allProduct")
    public HttpEntity<?> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int size) {
        List<Product> products = productService.getAllProduct(page, size);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Product product = productService.getOneById(id);
        return ResponseEntity.status(product != null ? 201 : 409).body(product);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        Product edited = productService.editProduct(id, productDTO);
        return ResponseEntity.status(edited != null ? 202 : 409).body(edited);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Integer id) {
        boolean delete = productService.deleted(id);
        if (delete)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }


    //top 10 talik mahsulot input un
    @GetMapping("/top")
    public HttpEntity<?> topInputProducts(@RequestParam String top) {
        ApiResponse response = productService.top(top);
        return ResponseEntity.ok(response);
    }

}
