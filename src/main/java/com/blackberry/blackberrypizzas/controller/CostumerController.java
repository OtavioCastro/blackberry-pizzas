package com.blackberry.blackberrypizzas.controller;

import com.blackberry.blackberrypizzas.controller.converter.CostumerAddDtoToCostumerConverter;
import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.request.CostumerAddDto;
import com.blackberry.blackberrypizzas.domain.request.UpdateCostumerDto;
import com.blackberry.blackberrypizzas.service.CostumerService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costumer")
@Log4j2
@AllArgsConstructor
public class CostumerController {

    private CostumerService costumerService;

    private CostumerAddDtoToCostumerConverter toCostumerConverter;

    @PostMapping
    public ResponseEntity<Costumer> addCostumer(@RequestBody CostumerAddDto costumerAddDto){
        log.info("Adicionando o cliente...");

        Costumer costumer = costumerService.addCostumer(toCostumerConverter.convert(costumerAddDto));

        return ResponseEntity.ok(costumer);
    }

    @PutMapping
    public ResponseEntity<Void> updateCostumer(@RequestBody UpdateCostumerDto updateCostumer){
        log.info("Atualizando dados do cliente...");

        costumerService.updateCostumer(updateCostumer);

        return ResponseEntity.noContent().build();
    }
}
