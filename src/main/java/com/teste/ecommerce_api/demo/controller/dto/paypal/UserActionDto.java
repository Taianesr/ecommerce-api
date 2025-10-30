package com.teste.ecommerce_api.demo.controller.dto.paypal;

public enum UserActionDto {
    CONTINUE, //depois de redirecionar o usuário para a páagina de pagamentos um botão de continuar aparece
    //use essa opçãao quaando a quantidade final não é conhecida quando o fluxo de checkout é iniciado e você quer redirecionar o cliente
    //para a página do comerciante sem processar o pagamento
    PAY_NOW //  depois de redirecionaar o usuáario paraa a páginaa de pagamentos, há um botãao chamado "PAGUE AGORA",
    //use esse botão quando o preço final é conhecido quando o checkout é iniciado e você quer processar o pagamento imediatamente quaando o customer clicar em "Pagar Agora"
}
