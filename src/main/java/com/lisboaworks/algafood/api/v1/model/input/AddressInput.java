package com.lisboaworks.algafood.api.v1.model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    @Valid
    @NotNull
    private CityIdInput city;

}
