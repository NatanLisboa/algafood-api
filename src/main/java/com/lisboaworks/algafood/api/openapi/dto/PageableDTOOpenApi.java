package com.lisboaworks.algafood.api.openapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableDTOOpenApi {

    @ApiModelProperty(value = "Page number (starts at 0)", example = "0")
    private int page;

    @ApiModelProperty(value = "Amount of elements in each page", example = "5")
    private int size;

    @ApiModelProperty(value = "Sort property name", example = "name,asc")
    private List<String> sort;

}
