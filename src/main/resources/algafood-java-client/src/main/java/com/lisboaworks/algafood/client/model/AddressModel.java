package com.lisboaworks.algafood.client.model;

import lombok.Data;

@Data
public class AddressModel {

    private String zipCode;
    private String streetName;
    private String number;
    private String complement;
    private String district;
    private CitySummaryModel city;

}
