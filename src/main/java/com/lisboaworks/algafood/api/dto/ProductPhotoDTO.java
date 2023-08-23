package com.lisboaworks.algafood.api.dto;

import com.lisboaworks.algafood.domain.model.Product;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ProductPhotoDTO {

    @ApiModelProperty(example = "d505a153-2d0b-4216-9ea6-0eef278e8137_tom-yum-goong.jpg")
    private String filename;

    @ApiModelProperty(example = "Spicy shrimp soup")
    private String description;

    @ApiModelProperty(example = "image/jpeg")
    private String contentType;

    @ApiModelProperty(example = "124568")
    private Long size;

}
