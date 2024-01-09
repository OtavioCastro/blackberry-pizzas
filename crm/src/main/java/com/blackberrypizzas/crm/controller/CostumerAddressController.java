package com.blackberrypizzas.crm.controller;

import com.blackberrypizzas.crm.domain.costumer.CostumerAddress;
import com.blackberrypizzas.crm.domain.request.CostumerAddresAddDto;
import com.blackberrypizzas.crm.service.CostumerAddressService;
import com.blackberrypizzas.crm.service.CostumerService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@Log4j2
@AllArgsConstructor
public class CostumerAddressController {

    final CostumerAddressService costumerAddressService;
    final CostumerService costumerService;

    @GetMapping
    public List<CostumerAddress> getCostumerAddress(@RequestParam String costumerCpf){
        return costumerAddressService.getCostumerAddressess(costumerCpf);
    }

    @GetMapping("/{addressId}")
    public CostumerAddress getCostumerAddresById(@PathVariable Long addressId){
        return costumerAddressService.getCostumerAddressById(addressId);
    }

    @PostMapping
    public void addCostumerAddress(@RequestBody CostumerAddresAddDto costumerAddresToAddDto){
        costumerService.saveCostumerAddress(costumerAddresToAddDto);
    }
}
