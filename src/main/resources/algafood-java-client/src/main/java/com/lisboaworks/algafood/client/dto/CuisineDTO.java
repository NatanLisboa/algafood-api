package com.lisboaworks.algafood.client.dto;

import lombok.Data;

@Data
public class CuisineDTO {

    private Long id;
    private String name;
    private CuisineDTO cuisine;

}
