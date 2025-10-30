package com.teste.ecommerce_api.demo.service;

import com.teste.ecommerce_api.demo.controller.dto.front.CartDto;
import com.teste.ecommerce_api.demo.controller.dto.front.FinalPriceDto;
import com.teste.ecommerce_api.demo.entity.ProductEntity;
import com.teste.ecommerce_api.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Service
public class CartService {

    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public FinalPriceDto calculateCartTotal(List<CartDto> cartsDto) throws ParseException {
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (CartDto cart : cartsDto) {
            ProductEntity product = productRepository.findBySku(cart.sku());

            String valueProduct = product.getUnitAmountProduct().getValue().replace(",", ".");

            BigDecimal unitPrice = new BigDecimal(valueProduct);
            BigDecimal quantity = BigDecimal.valueOf(cart.quantity());

            totalPrice = totalPrice.add(unitPrice.multiply(quantity));
        }

        return new FinalPriceDto(totalPrice);

    }
}

