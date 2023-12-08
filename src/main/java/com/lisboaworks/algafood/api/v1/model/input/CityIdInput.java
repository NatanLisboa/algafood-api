package com.lisboaworks.algafood.api.v1.model.input;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityIdInput {


	@NotNull
	private Long id;

}
