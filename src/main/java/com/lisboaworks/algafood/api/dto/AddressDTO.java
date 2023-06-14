package com.lisboaworks.algafood.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    private String zipCode;

    private String streetName;

    private String number;

    private String complement;

    private String district;

    private CitySummaryDTO city;

}
