package com.teste.ecommerce_api.demo.controller.dto.paypal;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {

    @JsonProperty("intent")
    private IntentDto intentDto;
    @JsonProperty("payment_source")
    private PaymentSourceDto paymentSourceDto;
    @JsonProperty("purchase_units")
    private List<PurchaseUnitsDto> purchaseUnitDtos;
}
