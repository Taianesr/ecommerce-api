package com.teste.ecommerce_api.demo.controller.dto.paypal;



public enum ShippingPreferenceDto {
    GET_FROM_FILE, //obtem o endereço de entrega fornecido pelo cliente no site do paypal
    NO_SHIPPING, // remove as informações de endereço de entrega da resposta da api e do site do paypal.
    SET_PROVIDED_ADDRESS // obtem o endereço fornecido pelo comerciante, o cliente não pode mudar esse endereço pelo site do paypal.
    //se o comerciante não passar o endereço ele pode escolher um nas páginas do paypal

}
