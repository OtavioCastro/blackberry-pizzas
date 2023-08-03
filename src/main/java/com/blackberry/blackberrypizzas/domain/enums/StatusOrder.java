package com.blackberry.blackberrypizzas.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusOrder {
    TO_DO(1,"To do"),
    IN_PROGRESS(2, "In progress"),
    READY(3, "Ready"),
    IN_DELIVERY(4, "In Delivery"),
    DELIVERED(5, "Delivered");

    private final Integer externalId;
    private final String status;
}
