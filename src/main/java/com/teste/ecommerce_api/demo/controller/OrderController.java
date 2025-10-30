package com.teste.ecommerce_api.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teste.ecommerce_api.demo.controller.dto.front.CartDto;
import com.teste.ecommerce_api.demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.teste.ecommerce_api.demo.util.PaypalUtils.extractLastHref;

@RestController
@Slf4j
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/order/v2")
    public ResponseEntity<Map<String, String>> order(@RequestBody List<CartDto> cartsDto) throws JsonProcessingException {

        log.info("Cards:"+ cartsDto);

        String msg = orderService.createOrder(cartsDto);
        String href = extractLastHref(msg);

        Map<String, String> response = new HashMap<>();
        response.put("href", href);

        return ResponseEntity.ok(response);
    }



}
