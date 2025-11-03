package com.teste.ecommerce_api.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.ecommerce_api.demo.controller.dto.paypal.OrderDto;
import com.teste.ecommerce_api.demo.entity.ProductEntity;
import com.teste.ecommerce_api.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class PaypalOrderService {

    private static final String CREATE_ORDER_URL = "https://api-m.sandbox.paypal.com/v2/checkout/orders";
    ObjectMapper mapper = new ObjectMapper();
    private final ProductRepository productRepository;

    public PaypalOrderService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createOrderPaypal(String accessToken, OrderDto orderDto, String requestId) throws JsonProcessingException {

//        for (PurchaseUnitsDto unit : orderDto.getPurchaseUnitDtos()) {
//            for (ItemsDto item : unit.getItems()) {
//                findProduct(item.getSku());
//            }
//        }
        return sendCreateOrderRequest(accessToken, orderDto, requestId);
    }

    public void findProduct(String sku) {
        ProductEntity product = productRepository.findBySku(sku);
        if (product != null) {
            System.out.println("Produto encontrado");
        }
        throw new NoSuchElementException("Produto não encontrado para o SKU: " + sku);

    }

    private String sendCreateOrderRequest(String accessToken, OrderDto orderDto, String requestId) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        log.info("Preparing HTTP request to create order in PayPal API...");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("PayPal-Request-Id", requestId);

        String jsonBody = mapper.writeValueAsString(orderDto);

        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(CREATE_ORDER_URL, request, String.class);

        if (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK) {
            log.info("Order successfully created in PayPal API.");
            return response.getBody();
        } else {
            throw new RuntimeException("Erro ao criar pedido no PayPal: " + response.getStatusCode());
        }
    }

}
