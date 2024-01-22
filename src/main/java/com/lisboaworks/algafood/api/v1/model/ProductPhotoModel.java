package com.lisboaworks.algafood.api.v1.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ProductPhotoModel extends RepresentationModel<ProductPhotoModel> {


    @Schema(example = "2e95d4d6-4335-47e9-95ab-7d3048c98c4b_tom-gha-kai.jpg")
    private String filename;

    @Schema(example = "Tom Gha Kai photo")
    private String description;

    @Schema(example = "image/jpeg")
    private String contentType;

    @Schema(example = "202443")
    private Long size;

}
