package com.teste.ecommerce_api.demo.controller.dto.paypal;

//unrestriced= aceita qualquer tipo de pagamento do cliente
//immediate payment required= aceita somente pagamentos imediatos do cliente. Por exemplo, cartão de crédito.
//garante que no tempo de captura, o pagamento não tem status pendente
public enum PaymentMethodPreferenceDto {
    UNRESTRICTED,
    IMMEDIATE_PAYMENT_REQUIRED
}
