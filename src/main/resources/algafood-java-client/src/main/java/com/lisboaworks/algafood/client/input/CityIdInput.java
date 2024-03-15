package com.lisboaworks.algafood.client.input;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class CityIdInput {

    @NotNull
    private Long id;

}
