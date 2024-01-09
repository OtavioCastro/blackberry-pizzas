package com.blackberrypizzas.crm.service.impl;

import com.blackberrypizzas.crm.domain.costumer.CostumerAddress;
import com.blackberrypizzas.crm.domain.request.CostumerAddresAddDto;
import com.blackberrypizzas.crm.repository.CostumerAddressRepository;
import com.blackberrypizzas.crm.repository.CostumerRepository;
import com.blackberrypizzas.crm.repository.entity.CostumerAddressEntity;
import com.blackberrypizzas.crm.repository.entity.CostumerEntity;
import com.blackberrypizzas.crm.service.CostumerAddressService;
import com.blackberrypizzas.crm.service.converter.CostumerAddressEntityToCostumerAddressConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

@Service
@AllArgsConstructor
public class CostumerAddressServiceImpl implements CostumerAddressService {

    private final CostumerAddressRepository costumerAddressRepository;
    private final CostumerAddressEntityToCostumerAddressConverter toCostumerAddressConverter;
    private final CostumerRepository costumerRepository;

    @Override
    public List<CostumerAddress> getCostumerAddressess(String costumerCpf) {
        CostumerEntity costumerEntity = costumerRepository.findByCostumerCpf(costumerCpf);
        return ofNullable(costumerEntity)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Cliente não encontrado"))
                .getCostumerAddresses()
                .stream()
                .map(toCostumerAddressConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CostumerAddress getCostumerAddressById(Long addressId) {
        return costumerAddressRepository.findById(addressId)
                .map(toCostumerAddressConverter::convert)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }

    public void saveCostumerAddress(CostumerAddresAddDto costumerAddressAddDto){
        CostumerAddressEntity costumerAddressEntity = CostumerAddressEntity.builder()
                .street(costumerAddressAddDto.getStreet())
                .city(costumerAddressAddDto.getCity())
                .neighborhood(costumerAddressAddDto.getNeighborhood())
                .postalCode(costumerAddressAddDto.getPostalCode())
                .build();
        costumerAddressRepository.save(costumerAddressEntity);
    }
}
