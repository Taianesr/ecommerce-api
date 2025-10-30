package com.teste.ecommerce_api.demo.controller.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemsDto {

    private String name;
    private String description;
    @JsonProperty("unit_amount")
    private UnitAmountDto unitAmountDto;
    private int quantity;
    private String category;
    private String sku;
    @JsonProperty("image_url")
    private String imageUrl;
    private String url;
    private UpcDto upc;
}
