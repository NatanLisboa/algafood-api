package com.lisboaworks.algafood.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantSummaryModel {

    private Long id;
    private String name;
    private BigDecimal shippingFee;


}
