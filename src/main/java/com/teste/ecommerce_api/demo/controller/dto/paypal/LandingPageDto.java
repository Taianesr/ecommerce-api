package com.teste.ecommerce_api.demo.controller.dto.paypal;

public enum LandingPageDto {
    LOGIN, // quando o cliente clica no checkout da Paypal, o cliente é redirecionado para a pagina de loginm da paypaal para aprovar o pagamento
    GUEST_CHECKOUT, // quando o cliente clica em Paypal Checkoutk, o cliente é redirecionado a uma página para adicionar o pagamento, se é débito ou crédito
    NO_PREFERENCE // quando o cliente é redirecionado para uma página para fazer o login no paypal e aprovar o pagamento ou para uma páagina para inserir o cartão de crédito ou débito e outras informações de fatuuramento relevantes necessárias para concluir a compra, dependendo da interação anterior dele com o Paypal.
}
