package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressModel {

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

    private CitySummaryModel city;

}
