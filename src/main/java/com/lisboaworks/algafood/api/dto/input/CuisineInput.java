package com.lisboaworks.algafood.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CuisineInput {
	
	@NotBlank
	private String name;

}
