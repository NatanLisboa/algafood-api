package com.lisboaworks.algafood.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {
	
	private Long id;
	private String name;
	private StateDTO state;

}
