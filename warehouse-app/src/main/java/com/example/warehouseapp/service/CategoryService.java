package com.example.warehouseapp.service;

import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.CategoryDTO;
import com.example.warehouseapp.payload.ResCategoryDTO;
import com.example.warehouseapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse save(CategoryDTO categoryDTO) {
        boolean existsByName = categoryRepository.existsByName(categoryDTO.getName());

        if (existsByName){
            return new ApiResponse("This Category already exist!!", false);
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());

        if(categoryDTO.getParentCategoryId() != null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if(!optionalCategory.isPresent())
                return new ApiResponse("Parent Category Not Found!",false);
            category.setParentCategory(categoryRepository.getById(categoryDTO.getParentCategoryId()));
        }

        Category save = categoryRepository.save(category);
        ResCategoryDTO resCategoryDTO = toResCat(save);


        return new ApiResponse("Saved", true,resCategoryDTO);
    }

    public ApiResponse delete(Integer id) {
        categoryRepository.deleteById(id);
        return new ApiResponse("Deleted!", true);
    }

    public ApiResponse edit(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (!optionalCategory.isPresent())
            return new ApiResponse("Category Not Found", false);

        Category category = optionalCategory.get();
        category.setName(categoryDTO.getName());
        category.setActive(categoryDTO.isActive());

        if(categoryDTO.getParentCategoryId() != null){
            Optional<Category> optional = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if(!optional.isPresent())
                return new ApiResponse("Parent Category Not Found",false);

            category.setParentCategory(categoryRepository.getById(categoryDTO.getParentCategoryId()));
        }

        Category save = categoryRepository.save(category);
        ResCategoryDTO resCategoryDTO = toResCat(save);

        return new ApiResponse("Edited!", true,resCategoryDTO);


    }

    public ApiResponse getAll() {
        List<Category> all = categoryRepository.findAll();
        List<ResCategoryDTO> resCategoryDTOList = toResCat(all);
        return  new ApiResponse("Mana", true,resCategoryDTOList);
    }

    public ApiResponse getOneById(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()){
            Category byId1 = categoryRepository.getById(id);
            ResCategoryDTO resCategoryDTO = toResCat(byId1);
            return new ApiResponse("Mana",true,byId1);
        }
        else {
            return new ApiResponse("Category Not Found!!!",false);
        }
    }

    public ApiResponse deleteAll() {
        categoryRepository.deleteAll();
        return new ApiResponse("All Category Deleted!",true);
    }

    public ApiResponse getChildCategories(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        List<Category> childCategories = categoryRepository.findAllByParentCategoryId(id);
        List<ResCategoryDTO> resCategoryDTOList = toResCat(childCategories);
        return new ApiResponse("Mana",true,resCategoryDTOList);
    }

    public ResCategoryDTO toResCat(Category category) {

        ResCategoryDTO resCategoryDTO = new ResCategoryDTO();
        resCategoryDTO.setId(category.getId());
        resCategoryDTO.setName(category.getName());
        if (category.getParentCategory()!=null){
            resCategoryDTO.setParentCategoryId(category.getParentCategory().getId());
        }else{
            resCategoryDTO.setParentCategoryId(null);
        }
        resCategoryDTO.setActive(category.isActive());
        resCategoryDTO.setCreatedBy(category.getCreatedBy());
        resCategoryDTO.setCreatedAt(category.getCreatedAt());
        resCategoryDTO.setUpdatedBy(category.getUpdatedBy());
        resCategoryDTO.setUpdatedAt(category.getUpdatedAt());
        return resCategoryDTO;
    }

    public List<ResCategoryDTO> toResCat(List<Category> category) {

        List<ResCategoryDTO> resCategoryDTOList = new ArrayList<>();

        for (Category category1 : category) {
            ResCategoryDTO resCategoryDTO = new ResCategoryDTO();
            resCategoryDTO.setId(category1.getId());
            resCategoryDTO.setName(category1.getName());
            if (category1.getParentCategory()!=null){
                resCategoryDTO.setParentCategoryId(category1.getParentCategory().getId());
            }else{
                resCategoryDTO.setParentCategoryId(null);
            }
            resCategoryDTO.setActive(category1.isActive());
            resCategoryDTO.setCreatedBy(category1.getCreatedBy());
            resCategoryDTO.setCreatedAt(category1.getCreatedAt());
            resCategoryDTO.setUpdatedBy(category1.getUpdatedBy());
            resCategoryDTO.setUpdatedAt(category1.getUpdatedAt());
            resCategoryDTOList.add(resCategoryDTO);
        }
        return resCategoryDTOList;
    }

}

