package com.teste.ecommerce_api.demo.controller.dto.front.Order;

import com.teste.ecommerce_api.demo.controller.dto.paypal.PurchaseUnitsDto;
import lombok.Data;

public record OrderDto(String intent,
                       PaymentSourceDto paymentSource,
                       PurchaseUnitsDto purchaseUnits) {
}

