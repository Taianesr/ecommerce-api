package com.teste.ecommerce_api.demo.controller.dto.front;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.ecommerce_api.demo.controller.dto.product.UnitAmountProductDto;
import com.teste.ecommerce_api.demo.controller.dto.product.UpcProductDto;


public record ProductFrontDto(Long id,
                              String name,
                              String description,
                              @JsonProperty("unit_amount_product")
                              UnitAmountProductDto unitAmountProduct,
                              String category,
                              String sku,
                              @JsonProperty("image_url")
                              String imageUrl,
                              String url,
                              UpcProductDto upc) {
}
