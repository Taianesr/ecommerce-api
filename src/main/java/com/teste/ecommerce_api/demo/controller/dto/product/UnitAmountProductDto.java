package com.teste.ecommerce_api.demo.controller.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitAmountProductDto {
    @JsonProperty("currency_code")
    private String currencyCode;
    private String value;
}