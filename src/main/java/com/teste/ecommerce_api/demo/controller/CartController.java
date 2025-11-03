package com.teste.ecommerce_api.demo.controller;

import com.teste.ecommerce_api.demo.controller.dto.front.CartDto;
import com.teste.ecommerce_api.demo.controller.dto.front.FinalPriceDto;
import com.teste.ecommerce_api.demo.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@Slf4j
@RestController
public class CartController {

    private final CartService cartService;


    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/final-price")
    public ResponseEntity<FinalPriceDto> calculateFinalPrice(@RequestBody List<CartDto> cartDto) throws ParseException {

        log.info("Received request to calculate cart final price. Request body: {}", cartDto);

        FinalPriceDto finalPriceDto = cartService.calculateCartTotal(cartDto);


        return ResponseEntity.ok(finalPriceDto);
    }

}
