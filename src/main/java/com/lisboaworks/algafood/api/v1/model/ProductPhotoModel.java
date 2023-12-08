package com.lisboaworks.algafood.api.v1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class ProductPhotoModel extends RepresentationModel<ProductPhotoModel> {


    private String filename;


    private String description;


    private String contentType;


    private Long size;

}
