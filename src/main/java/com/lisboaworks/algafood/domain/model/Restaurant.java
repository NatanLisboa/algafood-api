package com.lisboaworks.algafood.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Restaurant {

    @Id
    private Long id;

    private String name;

    private BigDecimal shippingFee;

}
