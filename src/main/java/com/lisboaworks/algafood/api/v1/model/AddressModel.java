package com.lisboaworks.algafood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "addresses")
@Getter
@Setter
public class AddressModel extends RepresentationModel<AddressModel> {


    private String zipCode;


    private String streetName;


    private String number;


    private String complement;


    private String district;

    private CitySummaryModel city;

}
