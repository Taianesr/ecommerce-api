package com.teste.ecommerce_api.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Embedded
    private UnitAmountProduct unitAmountProduct;
    private String category;
    private String sku;
    private String imageName;
    private String url;
    @Embedded
    private UpcProduct upc;

}
