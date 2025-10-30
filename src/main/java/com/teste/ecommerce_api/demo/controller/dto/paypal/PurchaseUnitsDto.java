package com.teste.ecommerce_api.demo.controller.dto.paypal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.teste.ecommerce_api.demo.controller.dto.paypal.purchaseUnits.AmountDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PurchaseUnitsDto {

    @JsonProperty("invoice_id")
    private String invoiceId;
    @JsonProperty("amount")
    private AmountDto amountDto;
    private List<ItemsDto> items;
    @JsonProperty("reference_id")
    private String referenceId;

}
