package com.lisboaworks.algafood.api.v1.openapi.model;



import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelOpenApi {


    private int page;


    private int size;


    private List<String> sort;

}
