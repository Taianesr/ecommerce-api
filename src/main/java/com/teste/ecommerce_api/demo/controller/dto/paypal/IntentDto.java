package com.teste.ecommerce_api.demo.controller.dto.paypal;

// capture= o comerciante pretende capturar o pagamento imediatamente após o cliente realizar o pagamento
// authorize= o comerciante pretende autorizar o pagamento e colocar os fundos em retenção após o cliente realizar o pagamento.
//(o valor pago não será imediatamente transferido ou disponibilizado para o comerciante. (os fundos ficam em retenção antes de serem liberados).

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IntentDto {

    CAPTURE, AUTHORIZE;

    @JsonValue
    public String toValue() {
        return this.name();
    }

    @JsonCreator
    public static IntentDto fromValue(String value) {
        for (IntentDto intentDto : IntentDto.values()) {
            if (intentDto.name().equalsIgnoreCase(value)) {
                return intentDto;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Intent: " + value);
    }
}
