package com.lisboaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class AddressInput {

    @NotBlank
    @Schema(example = "38400-000")
    private String zipCode;

    @NotBlank
    @Schema(example = "St. Floriano Peixoto")
    private String streetName;

    @NotBlank
    @Schema(example = "500")
    private String number;

    @Schema(example = "Apt. 801")
    private String complement;

    @NotBlank
    @Schema(example = "Brazil")
    private String district;

    @Valid
    @NotNull
    private CityIdInput city;

}
