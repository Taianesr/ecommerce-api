package com.teste.ecommerce_api.demo.controller.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemTotalDto {
    @JsonProperty("currency_code")
    private String currencyCode;
    private String value;
}
