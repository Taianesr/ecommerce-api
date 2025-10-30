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
public class BreakdownDto {
    @JsonProperty("item_total")
    private ItemTotalDto itemTotal;
    @JsonProperty("shipping")
    private ShippingDto shippingDto;
}
