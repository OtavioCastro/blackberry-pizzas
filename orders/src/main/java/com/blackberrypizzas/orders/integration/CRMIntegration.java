package com.blackberrypizzas.orders.integration;

import com.blackberrypizzas.orders.domain.costumer.Costumer;
import com.blackberrypizzas.orders.domain.costumer.CostumerAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "crm-client", url = "${blackberry.url.crm}")
public interface CRMIntegration {

    @GetMapping("/costumer")
    Costumer getCostumer(@RequestParam("costumerCpf") String costumerCpf);

    @GetMapping("/address/{addressId}")
    CostumerAddress getCostumerAddressById(@PathVariable("addressId") Long addressId);
}
