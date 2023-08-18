package com.lisboaworks.algafood.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    @ApiModelProperty(example = "08140000")
    private String zipCode;

    @ApiModelProperty(example = "St. One")
    private String streetName;

    @ApiModelProperty(example = "2")
    private String number;

    @ApiModelProperty(example = "Apartment 1A")
    private String complement;

    @ApiModelProperty(example = "John Plaza")
    private String district;

    private CitySummaryDTO city;

}
