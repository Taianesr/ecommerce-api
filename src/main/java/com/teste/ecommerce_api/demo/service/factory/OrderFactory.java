package com.teste.ecommerce_api.demo.service.factory;

import com.teste.ecommerce_api.demo.controller.dto.paypal.*;
import com.teste.ecommerce_api.demo.controller.dto.paypal.purchaseUnits.AmountDto;
import com.teste.ecommerce_api.demo.controller.dto.paypal.purchaseUnits.BreakdownDto;
import com.teste.ecommerce_api.demo.controller.dto.paypal.purchaseUnits.ShippingDto;
import com.teste.ecommerce_api.demo.entity.ProductEntity;
import com.teste.ecommerce_api.demo.service.BucketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class OrderFactory {

    private final BucketService bucketService;

    public OrderFactory(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    public List<ItemsDto> buildItem(Map<ProductEntity, Integer> products) {

        ArrayList<ItemsDto> itemsDtos = new ArrayList<>();


        for (Map.Entry<ProductEntity, Integer> entry : products.entrySet()) {

            ProductEntity product = entry.getKey();
            Integer quantity = entry.getValue();

            UnitAmountDto unitAmount = UnitAmountDto.builder()
                    .value(product.getUnitAmountProduct().getValue())
                    .currencyCode(product.getUnitAmountProduct().getCurrencyCode())
                    .build();

            itemsDtos.add(ItemsDto.builder()
                    .name(product.getName())
                    .quantity(quantity)
                    .description(product.getDescription())
                    .unitAmountDto(unitAmount)
                    .category(product.getCategory())
                    .sku(product.getSku())
                    .imageUrl(bucketService.generatePreAssigendUrl(product.getImageName()))
                    .url(product.getUrl())
                    .build());
        }

        return itemsDtos;
    }

    public AmountDto buildAmount(Map<ProductEntity, Integer> products) {

        BigDecimal itemTotal = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);

        for (Map.Entry<ProductEntity, Integer> entry : products.entrySet()) {
            ProductEntity product = entry.getKey();
            Integer quantity = entry.getValue();

            itemTotal = itemTotal.add(new BigDecimal( product.getUnitAmountProduct().getValue())
                    .multiply(BigDecimal.valueOf(quantity)));

        }


        ItemTotalDto itemTotalDto = ItemTotalDto.builder()
                .value(itemTotal.toString())
                .currencyCode("BRL")
                .build();

        ShippingDto shippingDto = ShippingDto.builder()
                .value("10.00")
                .currencyCode("BRL")
                .build();

       BreakdownDto breakdownDto = BreakdownDto.builder()
                .itemTotal(itemTotalDto)
                .shippingDto(shippingDto)
                .build();

        total = total.add(itemTotal.add(new BigDecimal(shippingDto.getValue())));


        return AmountDto.builder()
                .value(total.toString())
                .currencyCode("BRL")
                .breakdownDto(breakdownDto)
                .build();

    }

    public PaypalDto buildPaypal() {
        ExperienceContextDto context = ExperienceContextDto.builder()
                .paymentMethodPreferenceDto(PaymentMethodPreferenceDto.IMMEDIATE_PAYMENT_REQUIRED)
                .landingPageDto(LandingPageDto.GUEST_CHECKOUT)
                .shippingPreferenceDto(ShippingPreferenceDto.GET_FROM_FILE)
                .userActionDto(UserActionDto.PAY_NOW)
                .returnUrl("https://example.com/returnUrl")
                .cancelUrl("https://example.com/cancelUrl")
                .build();

        return PaypalDto.builder().experienceContextDto(context).build();
    }

    public PurchaseUnitsDto buildPurchaseUnit(List<ItemsDto> items, AmountDto amount) {
        return PurchaseUnitsDto.builder()
                .referenceId("default")
                .items(items)
                .amountDto(amount)
                .invoiceId(UUID.randomUUID().toString())
                .build();
    }

    public OrderDto buildOrder(PurchaseUnitsDto purchaseUnit, PaypalDto paypal) {
        return OrderDto.builder()
                .intentDto(IntentDto.CAPTURE)
                .paymentSourceDto(PaymentSourceDto.builder().paypalDto(paypal).build())
                .purchaseUnitDtos(List.of(purchaseUnit))
                .build();
    }

}
