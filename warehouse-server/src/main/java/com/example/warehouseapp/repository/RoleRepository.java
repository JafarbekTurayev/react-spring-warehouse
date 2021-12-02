package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    boolean existsByName(String name);
}
