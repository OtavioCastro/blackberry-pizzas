package com.blackberrypizzas.orders.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusOrder {
    TO_DO(1,"Para fazer"),
    IN_PROGRESS(2, "Em preparação"),
    READY(3, "Pronto"),
    IN_DELIVERY(4, "Em rota de entrega"),
    DELIVERED(5, "Entregue"),
    CANCELED(6, "Cancelado");

    private final Integer externalId;
    @JsonValue
    private final String status;
}
