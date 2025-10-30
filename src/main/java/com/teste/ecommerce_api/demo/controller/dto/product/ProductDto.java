package com.teste.ecommerce_api.demo.controller.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ProductDto(Long id,
                         String name,
                         String description,
                         @JsonProperty("unit_amount_product")
                         UnitAmountProductDto unitAmountProduct,
                         String category,
                         String sku,
                         @JsonProperty("image_name")
                         String imageName,
                         String url,
                         @JsonProperty("upc_product")
                         UpcProductDto upcProduct) {
}
