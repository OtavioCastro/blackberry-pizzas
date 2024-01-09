package com.blackberrypizzas.crm.service.impl;

import com.blackberrypizzas.crm.domain.costumer.Costumer;
import com.blackberrypizzas.crm.domain.request.CostumerAddDto;
import com.blackberrypizzas.crm.domain.request.CostumerAddresAddDto;
import com.blackberrypizzas.crm.domain.request.UpdateCostumerDto;
import com.blackberrypizzas.crm.repository.CostumerRepository;
import com.blackberrypizzas.crm.repository.entity.CostumerAddressEntity;
import com.blackberrypizzas.crm.repository.entity.CostumerEntity;
import com.blackberrypizzas.crm.service.CostumerAddressService;
import com.blackberrypizzas.crm.service.CostumerService;
import com.blackberrypizzas.crm.service.converter.CostumerAddressToCostumerAddressEntityConverter;
import com.blackberrypizzas.crm.service.converter.CostumerEntityToCostumerConverter;
import com.blackberrypizzas.crm.service.converter.CostumerToCostumerEntityConverter;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Log4j2
@AllArgsConstructor
@Service
public class CostumerServiceImpl implements CostumerService {

    private CostumerRepository costumerRepository;

    private CostumerEntityToCostumerConverter toCostumerConverter;

    private CostumerToCostumerEntityConverter toCostumerEntityConverter;

    private CostumerAddressToCostumerAddressEntityConverter toCostumerAddressEntityConverter;

    private CostumerAddressService costumerAddressService;

    @Override
    public Costumer findCostumer(String costumerCpf) {
        return Optional.ofNullable(costumerRepository.findByCostumerCpf(costumerCpf))
                .map(toCostumerConverter::convert)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @Override
    @Transactional
    public Costumer addCostumer(CostumerAddDto costumerAddDto) {
        Optional<Costumer> costumerOptional = Optional.ofNullable(
                costumerRepository.findByCostumerCpf(costumerAddDto.getCpf())
        ).map(toCostumerConverter::convert);

        if (costumerOptional.isPresent()) {
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cliente já cadastrado!");
        } else {
            Costumer costumer = Costumer.builder()
                    .name(costumerAddDto.getCostumerName())
                    .cpf(costumerAddDto.getCpf())
                    .costumerPhone(costumerAddDto.getCostumerPhone())
                    .costumerAddresses(List.of(costumerAddDto.getCostumerAddress()))
                    .build();
            buildCostumerEntity(costumer);
            return costumer;
        }
    }

    @Override
    public void updateCostumer(UpdateCostumerDto updateCostumerDto) {
        Optional<CostumerEntity> costumertoUpdate = Optional.ofNullable(costumerRepository.findByCostumerCpf(updateCostumerDto.getCpf()));

        if (costumertoUpdate.isPresent()) {
            CostumerEntity costumerToSave = costumertoUpdate.get();
            costumerToSave.setCostumerPhone(updateCostumerDto.getCostumerPhone());
            costumerRepository.save(costumertoUpdate.get());
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
        }
    }

    @Override
    @Transactional
    public void saveCostumerAddress(CostumerAddresAddDto costumerAddressAddDto){
        CostumerEntity costumerEntity = costumerRepository.findByCostumerCpf(costumerAddressAddDto.getCostumerCpf());
        CostumerAddressEntity costumerAddressEntity = CostumerAddressEntity.builder()
                .street(costumerAddressAddDto.getStreet())
                .city(costumerAddressAddDto.getCity())
                .neighborhood(costumerAddressAddDto.getNeighborhood())
                .postalCode(costumerAddressAddDto.getPostalCode())
                .build();
        costumerEntity.getCostumerAddresses().add(costumerAddressEntity);
        costumerRepository.save(costumerEntity);
    }

    private void buildCostumerEntity(Costumer costumer) {
        log.log(Level.INFO, "Cliente não cadastrado! Realizando o cadastro...");

        CostumerEntity costumerEntity = toCostumerEntityConverter.convert(costumer);
        costumerRepository.save(costumerEntity);
    }

}
