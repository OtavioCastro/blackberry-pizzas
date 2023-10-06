package com.blackberry.blackberrypizzas.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "COSTUMER")
public class CostumerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String cpf;
    private String costumerPhone;
    @OneToOne(cascade = CascadeType.PERSIST)
    private CostumerAddressEntity costumerAddress;
}
