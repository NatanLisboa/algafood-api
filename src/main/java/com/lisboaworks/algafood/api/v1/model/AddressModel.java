package com.lisboaworks.algafood.api.v1.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "addresses")
@Getter
@Setter
public class AddressModel extends RepresentationModel<AddressModel> {

    @Schema(example = "38400-000")
    private String zipCode;

    @Schema(example = "St. Floriano Peixoto")
    private String streetName;

    @Schema(example = "500")
    private String number;

    @Schema(example = "Apt. 801")
    private String complement;

    @Schema(example = "Brazil")
    private String district;

    private CitySummaryModel city;

}
