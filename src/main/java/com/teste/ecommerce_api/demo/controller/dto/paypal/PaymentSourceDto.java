package com.teste.ecommerce_api.demo.controller.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class PaymentSourceDto {

    @JsonProperty("paypal")
    private PaypalDto paypalDto;

}
