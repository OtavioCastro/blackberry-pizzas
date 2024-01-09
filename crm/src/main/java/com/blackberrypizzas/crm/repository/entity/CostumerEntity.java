package com.blackberrypizzas.crm.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "COSTUMER")
public class CostumerEntity {

    @Id
    private String costumerCpf;
    private String name;
    private String costumerPhone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CostumerAddressEntity> costumerAddresses;
}
