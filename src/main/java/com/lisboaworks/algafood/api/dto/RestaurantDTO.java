package com.lisboaworks.algafood.api.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
	
	private Long id;
	private String name;
	private BigDecimal fee;
	private CuisineDTO cuisine;
	
	/*
	 * Source: cuisine,name
	 * Target: name,cuisine
	 */
	private String nameCuisine;
	private Long idCuisine;
	
}
