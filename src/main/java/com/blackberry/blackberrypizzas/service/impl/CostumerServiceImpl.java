package com.blackberry.blackberrypizzas.service.impl;

import com.blackberry.blackberrypizzas.domain.costumer.Costumer;
import com.blackberry.blackberrypizzas.domain.request.UpdateCostumerDto;
import com.blackberry.blackberrypizzas.repository.CostumerRepository;
import com.blackberry.blackberrypizzas.repository.entity.CostumerAddressEntity;
import com.blackberry.blackberrypizzas.repository.entity.CostumerEntity;
import com.blackberry.blackberrypizzas.service.CostumerService;
import com.blackberry.blackberrypizzas.service.converter.CostumerAddressToCostumerAddressEntityConverter;
import com.blackberry.blackberrypizzas.service.converter.CostumerEntityToCostumerConverter;
import com.blackberry.blackberrypizzas.service.converter.CostumerToCostumerEntityConverter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

import static java.util.Objects.nonNull;

@Log4j2
@AllArgsConstructor
@Service
public class CostumerServiceImpl implements CostumerService {

    private CostumerRepository costumerRepository;

    private CostumerEntityToCostumerConverter toCostumerConverter;

    private CostumerToCostumerEntityConverter toCostumerEntityConverter;

    private CostumerAddressToCostumerAddressEntityConverter toCostumerAddressEntityConverter;

    @Override
    public Costumer addCostumer(Costumer costumerAddDto) {
        Optional<Costumer> costumerOptional = Optional.ofNullable(
                costumerRepository.findByCpf(costumerAddDto.getCpf())
        ).map(toCostumerConverter::convert);

        if(costumerOptional.isPresent()){
            throw new HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY, "Cliente já cadastrado!");
        } else {
            Costumer costumer = Costumer.builder()
                    .name(costumerAddDto.getName())
                    .cpf(costumerAddDto.getCpf())
                    .costumerPhone(costumerAddDto.getCostumerPhone())
                    .costumerAddress(costumerAddDto.getCostumerAddress())
                    .build();
            buildCostumerEntity(costumer);
            return costumer;
        }
    }

    @Override
    public void updateCostumer(UpdateCostumerDto updateCostumerDto) {
        Optional<CostumerEntity> costumertoUpdate = Optional.ofNullable(costumerRepository.findByCpf(updateCostumerDto.getCpf()));

        if(costumertoUpdate.isPresent()){
            CostumerEntity costumerToSave = costumertoUpdate.get();
            costumerToSave.setCostumerPhone(updateCostumerDto.getCostumerPhone());
            if(nonNull(updateCostumerDto.getCostumerAddress())){
                CostumerAddressEntity costumerAddressEntity = toCostumerAddressEntityConverter.convert(updateCostumerDto.getCostumerAddress());
                costumerToSave.setCostumerAddress(costumerAddressEntity);
            }
            costumerRepository.save(costumertoUpdate.get());
        } else {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
        }
    }

    private void buildCostumerEntity(Costumer costumer) {
        log.log(Level.INFO, "Cliente não cadastrado! Realizando o cadastro...");

        CostumerEntity costumerEntity = toCostumerEntityConverter.convert(costumer);
        costumerRepository.save(costumerEntity);
    }

}
