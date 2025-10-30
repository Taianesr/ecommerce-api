package com.teste.ecommerce_api.demo.controller.dto.front.Order;

import com.teste.ecommerce_api.demo.controller.dto.paypal.PaypalDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentSourceDto {
    private PaypalDto paypal;
}
