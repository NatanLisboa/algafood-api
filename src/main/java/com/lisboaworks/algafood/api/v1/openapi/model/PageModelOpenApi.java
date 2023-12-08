package com.lisboaworks.algafood.api.v1.openapi.model;



import lombok.Getter;
import lombok.Setter;

@ApiModel("PageModel")
@Getter
@Setter
public class PageModelOpenApi {


    private Long size;


    private Long totalElements;


    private Long totalPages;


    private Long number;

}
