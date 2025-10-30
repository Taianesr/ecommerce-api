package com.teste.ecommerce_api.demo.controller.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaypalDto {

    @JsonProperty("experience_context")
    private ExperienceContextDto experienceContextDto;

}
