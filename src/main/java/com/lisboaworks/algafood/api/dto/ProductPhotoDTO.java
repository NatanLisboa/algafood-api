package com.lisboaworks.algafood.api.dto;

import com.lisboaworks.algafood.domain.model.Product;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class ProductPhotoDTO {

    private String filename;
    private String description;
    private String contentType;
    private Long size;

}
