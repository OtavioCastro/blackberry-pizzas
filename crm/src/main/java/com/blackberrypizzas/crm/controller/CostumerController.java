package com.blackberrypizzas.crm.controller;


import com.blackberrypizzas.crm.controller.converter.CostumerAddDtoToCostumerConverter;
import com.blackberrypizzas.crm.controller.dto.CostumerRecordDto;
import com.blackberrypizzas.crm.domain.costumer.Costumer;
import com.blackberrypizzas.crm.domain.request.CostumerAddDto;
import com.blackberrypizzas.crm.domain.request.UpdateCostumerDto;
import com.blackberrypizzas.crm.service.CostumerService;
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

    @GetMapping
    public ResponseEntity<CostumerRecordDto> getCostumer(@RequestParam String costumerCpf){
        log.info("Buscando o cliente de número... " + costumerCpf);

        Costumer costumer = costumerService.findCostumer(costumerCpf);
        CostumerRecordDto costumerRecordDto = new CostumerRecordDto(
                costumer.getCostumerPhone(),
                costumer.getCpf(),
                costumer.getName(),
                costumer.getCostumerAddresses());

        return ResponseEntity.ok(costumerRecordDto);
    }

    @PostMapping
    public ResponseEntity<Costumer> addCostumer(@RequestBody CostumerAddDto costumerAddDto){
        log.info("Adicionando o cliente...");

        Costumer costumer = costumerService.addCostumer(costumerAddDto);

        return ResponseEntity.ok(costumer);
    }

    @PutMapping
    public ResponseEntity<Void> updateCostumer(@RequestBody UpdateCostumerDto updateCostumer){
        log.info("Atualizando dados do cliente...");

        costumerService.updateCostumer(updateCostumer);

        return ResponseEntity.noContent().build();
    }
}
