package com.blackberry.blackberrypizzas.domain.request;

import com.blackberry.blackberrypizzas.domain.enums.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusOrderUpdateRequest {
    private String orderNumber;
    private StatusOrder statusOrder;
}
