package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByPhoneNumber(String phone);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByCode(Integer code);

    @Query(value = "select * from users where users.id in(select users_id from users_ware_houses where ware_houses_id=:warehouseId)", nativeQuery = true)
    List<User> getAllByWarehouseId(Integer warehouseId);



}
