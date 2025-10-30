package com.teste.ecommerce_api.demo.controller.dto.front;

import java.math.BigDecimal;

public record FinalPriceDto(String value) {
    public FinalPriceDto(BigDecimal value) {
        this(String.format("%.2f", value));
    }
}
