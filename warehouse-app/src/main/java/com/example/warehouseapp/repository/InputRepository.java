package com.example.warehouseapp.repository;

import com.example.warehouseapp.entity.Input;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface InputRepository extends JpaRepository<Input, Integer> {

    List<Input> findByCurrency_Id(Integer currency_id);


    List<Input> findAllByDateBetween(Date from, Date to);

    //select s.name, p.name, input.date, ip.amount
    @Query(value = " select * from input inner join supplier s on s.id = input.supplier_id inner join input_product ip on input.id = ip.input_id inner join product p on p.id = ip.product_id", nativeQuery = true)
    List<Input> getAllHistory();

    List<Input> findAllBySupplier_Id(Integer sId);

    List<Input> findAllBySupplier_IdAndDateBetween(Integer sId, Date from, Date to);

//    List<Input> findAllByDateBetween(Timestamp from, Timestamp to);

    List<Input> findAllByWarehouse_Name(String id);
}
