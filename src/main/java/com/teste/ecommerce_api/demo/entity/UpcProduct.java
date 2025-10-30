package com.teste.ecommerce_api.demo.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class UpcProduct {
    private String type;
    private String code;
}
