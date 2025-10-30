package com.teste.ecommerce_api.demo.controller.dto.paypal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpcDto {
    private String type;
    private String code;
}
