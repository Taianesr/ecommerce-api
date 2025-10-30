package com.teste.ecommerce_api.demo.controller.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExperienceContextDto {
    @JsonProperty("payment_method_preference")
    private PaymentMethodPreferenceDto paymentMethodPreferenceDto;
    @JsonProperty("brand_name")
    private String brandName;
    @JsonProperty("landing_page")
    private LandingPageDto landingPageDto;
    @JsonProperty("shipping_preference")
    private ShippingPreferenceDto shippingPreferenceDto;
    @JsonProperty("user_action")
    private UserActionDto userActionDto;
    @JsonProperty("return_url")
    private String returnUrl;
    @JsonProperty("cancel_url")
    private String cancelUrl;

}
