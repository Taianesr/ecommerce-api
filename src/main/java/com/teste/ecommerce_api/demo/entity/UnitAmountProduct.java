package com.teste.ecommerce_api.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.ecommerce_api.demo.controller.dto.paypal.UnitAmountDto;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class UnitAmountProduct {
    @JsonProperty("currency_code")
    private String currencyCode;
    private String value;
}
