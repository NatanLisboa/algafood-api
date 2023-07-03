package com.lisboaworks.algafood.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatus {

    CREATED("Created"),
    CONFIRMED("Confirmed"),
    DELIVERED("Delivered"),
    CANCELLED("Cancelled");

    private String description;

}
