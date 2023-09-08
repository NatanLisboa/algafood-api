package com.lisboaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ProductPhotoModel extends RepresentationModel<ProductPhotoModel> {

    @ApiModelProperty(example = "d505a153-2d0b-4216-9ea6-0eef278e8137_tom-yum-goong.jpg")
    private String filename;

    @ApiModelProperty(example = "Spicy shrimp soup")
    private String description;

    @ApiModelProperty(example = "image/jpeg")
    private String contentType;

    @ApiModelProperty(example = "124568")
    private Long size;

}
