package com.lisboaworks.algafood.api.openapi.dto;

import com.lisboaworks.algafood.api.dto.CuisineDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginatedDTOOpenAPI<T> {

    @ApiModelProperty("Total elements")
    private List<T> content;

    @ApiModelProperty(example = "10", value = "Number of records per page")
    private Long size;

    @ApiModelProperty(example = "50", value = "Total records")
    private Long totalElements;

    @ApiModelProperty(example = "5", value = "Total pages")
    private Long totalPages;

    @ApiModelProperty(example = "0", value = "Page number (starts at 0)")
    private Long number;

}
