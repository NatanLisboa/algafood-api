package com.lisboaworks.algafood.client.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestaurantSummaryDTO {

    private Long id;
    private String name;
    private BigDecimal shippingFee;


}
