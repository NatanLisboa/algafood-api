package com.lisboaworks.algafood.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInput {

    @ApiModelProperty(example = "08140000")
    @NotBlank
    private String zipCode;

    @ApiModelProperty(example = "St. One")
    @NotBlank
    private String streetName;

    @ApiModelProperty(example = "2")
    @NotBlank
    private String number;

    @ApiModelProperty(example = "Apartment 1A")
    private String complement;

    @ApiModelProperty(example = "St. John")
    @NotBlank
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;

}
