package com.teste.ecommerce_api.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.teste.ecommerce_api.demo.controller.dto.front.CartDto;
import com.teste.ecommerce_api.demo.controller.dto.paypal.*;
import com.teste.ecommerce_api.demo.controller.dto.paypal.purchaseUnits.AmountDto;
import com.teste.ecommerce_api.demo.entity.ProductEntity;
import com.teste.ecommerce_api.demo.service.factory.OrderFactory;
import com.teste.ecommerce_api.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class OrderService {

    private final ProductRepository productRepository;

    private final PaypalOrderService paypalOrderService;

    private final PaypalAuthService paypalAuthService;

    private final OrderFactory orderFactory;

    public OrderService(ProductRepository productRepository, PaypalOrderService paypalOrderService, PaypalAuthService paypalAuthService, OrderFactory orderFactory) {
        this.productRepository = productRepository;
        this.paypalOrderService = paypalOrderService;
        this.paypalAuthService = paypalAuthService;
        this.orderFactory = orderFactory;
    }

    public String createOrder(List<CartDto> cartsDto) throws JsonProcessingException {

        Map<ProductEntity, Integer> productQuantities = new HashMap<>();

        for (CartDto cartDto : cartsDto) {

            ProductEntity product = productRepository.findBySku(cartDto.sku());

            String valueProduct = product.getUnitAmountProduct().getValue().replace(",", ".");
            product.getUnitAmountProduct().setValue(valueProduct);

            productQuantities.put(product, cartDto.quantity());

        }


        ArrayList<ItemsDto> items = new ArrayList<>(orderFactory.buildItem(productQuantities));


        AmountDto amount = orderFactory.buildAmount(productQuantities);
        PaypalDto paypal = orderFactory.buildPaypal();
        PurchaseUnitsDto purchaseUnit = orderFactory.buildPurchaseUnit(items, amount);
        OrderDto order = orderFactory.buildOrder(purchaseUnit, paypal);

        log.info("" + order);

        return paypalOrderService.createOrderPaypal(
                paypalAuthService.fetchPaypalAccessToken(), order, UUID.randomUUID().toString()
        );


    }


}
