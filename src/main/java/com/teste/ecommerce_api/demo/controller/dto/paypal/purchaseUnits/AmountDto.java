package com.teste.ecommerce_api.demo.controller.dto.paypal.purchaseUnits;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.ecommerce_api.demo.controller.dto.paypal.ItemTotalDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AmountDto {
    @JsonProperty("currency_code")
    private String currencyCode;
    @JsonProperty("value")
    private String value;
    @JsonProperty("breakdown")
    private BreakdownDto breakdownDto;

}
