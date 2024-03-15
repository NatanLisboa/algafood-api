package com.lisboaworks.algafood.client.input;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class AddressInput {

    @NotBlank
    private String zipCode;

    @NotBlank
    private String streetName;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String district;

    @NotNull
    private CityIdInput city;

}
