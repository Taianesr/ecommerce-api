package com.teste.ecommerce_api.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.ecommerce_api.demo.controller.dto.paypal.OrderDto;
import com.teste.ecommerce_api.demo.service.PaypalAuthService;
import com.teste.ecommerce_api.demo.service.PaypalOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckoutController {

    private final PaypalAuthService paypalAuthService;
    private final PaypalOrderService paypalOrderService;

    public CheckoutController(PaypalAuthService paypalAuthService, PaypalOrderService paypalOrderService) {

        this.paypalAuthService = paypalAuthService;
        this.paypalOrderService = paypalOrderService;
    }

    @PostMapping("/order")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) throws JsonProcessingException {
        String token = paypalAuthService.fetchPaypalAccessToken();
        String resultado = paypalOrderService.createOrderPaypal(token, orderDto, "7b92603e-77ed-4896-8e78-5dea2050476a");
        return ResponseEntity.ok(resultado);
    }


}

