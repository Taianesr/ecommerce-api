package com.teste.ecommerce_api.demo.controller.dto.paypal.purchaseUnits;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingDto {
    @JsonProperty("currency_code")
    private String currencyCode;
    private String value;
}
