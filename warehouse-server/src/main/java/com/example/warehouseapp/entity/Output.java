package com.example.warehouseapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date date;

    @ManyToOne
    private Warehouse warehouse;
    @ManyToOne
    private Currency currency;
    @Column(nullable = false, unique = true)
    private String factureNumber;

    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "output", cascade = CascadeType.ALL)
    private List<OutputProduct> outputProductList;

}
