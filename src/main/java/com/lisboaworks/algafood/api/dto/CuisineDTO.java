package com.lisboaworks.algafood.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineDTO {
	
	private Long id;
	
	/*
	 * Source: cuisine,name
	 * Target: cuisine,cuisine,name
	 */
	private String cuisineName;

}
