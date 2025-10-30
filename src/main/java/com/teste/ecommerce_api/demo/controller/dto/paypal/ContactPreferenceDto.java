package com.teste.ecommerce_api.demo.controller.dto.paypal;

public enum ContactPreferenceDto {
    NO_CONTACT_INFO, // o comerciante pode não optar por mostrar as informações de contato do comprador
    UPDATE_CONTACT_INFO, //  o comerciante permite que o comprador adicione ou atualize informações de entrega no checkout da Paypal.
    REATAIN_CONTACT_INFO // o comprador pode ver mas não pode sobreescrever dados de informaçãao passados pelo comerciante
}
