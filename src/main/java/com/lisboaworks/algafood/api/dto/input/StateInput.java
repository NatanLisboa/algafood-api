package com.lisboaworks.algafood.api.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StateInput {
	
	@NotBlank
	private String name;

}
