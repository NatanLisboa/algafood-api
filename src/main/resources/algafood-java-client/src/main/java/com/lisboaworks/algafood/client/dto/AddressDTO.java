package com.lisboaworks.algafood.client.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private String zipCode;
    private String streetName;
    private String number;
    private String complement;
    private String district;
    private CitySummaryDTO city;

}
