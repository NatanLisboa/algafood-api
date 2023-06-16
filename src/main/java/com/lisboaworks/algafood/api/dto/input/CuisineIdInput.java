package com.lisboaworks.algafood.api.dto.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CuisineIdInput {
	
	@NotNull
	private Long id;

}
