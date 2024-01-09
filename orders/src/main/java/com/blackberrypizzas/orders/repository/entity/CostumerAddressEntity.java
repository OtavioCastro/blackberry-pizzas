package com.blackberrypizzas.orders.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "COSTUMER_ADDRESS")
public class CostumerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String street;
    private String postalCode;
    private String neighborhood;
    private String city;

}
