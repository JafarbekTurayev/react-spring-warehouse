package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.entity.Measurement;
import com.example.warehouseapp.entity.Product;
import com.example.warehouseapp.exception.ResourceNotFoundException;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.ProductDTO;
import com.example.warehouseapp.repository.CategoryRepository;
import com.example.warehouseapp.repository.MeasurementRepository;
import com.example.warehouseapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        if (!productRepository.existsByName(productDTO.getName())) {
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasureId());

            Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCatId());
            Category category = categoryRepository.findById(productDTO.getCatId())
                    .orElseThrow(() -> new ResourceNotFoundException("category", "id", productDTO.getCatId()));

            product.setCategory(category);
//            product.setCategory(optionalCategory.get());
            product.setMeasurement(optionalMeasurement.get());

            product.setName(productDTO.getName());

            product.setCode(UUID.randomUUID().toString());

            productRepository.save(product);
            return new ApiResponse("Saved!", true);
        }
        return new ApiResponse("Bunday mahsulot bor!", false);
    }


    public List<Product> getAllProduct(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> allProduct = productRepository.findAll(pageable);
        return allProduct.getContent();
    }

    public Product getOneById(Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
    }

    public Product editProduct(Integer id, ProductDTO productDTO) {
        Optional<Product> byId = productRepository.findById(id);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getCatId());
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCatId());
        if (byId.isPresent()) {
            Product editProduct = byId.get();
            editProduct.setName(productDTO.getName());
            editProduct.setCategory(optionalCategory.get());
            editProduct.setMeasurement(optionalMeasurement.get());
            return productRepository.save(editProduct);
        }
        return null;
    }

    public boolean deleted(Integer id) {
        try {
            productRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ApiResponse top(String top) {
//        if (top.equals("desc")) {
            return new ApiResponse("Mana", true, productRepository.getTopInputProducts(top));
//        } else {
//            //umuman sotilmagan
//            return new ApiResponse("Mana", true, productRepository.getLessInputProducts(String top));
//        }
    }
}
