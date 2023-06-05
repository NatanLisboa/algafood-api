package com.lisboaworks.algafood.api.dto.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lisboaworks.algafood.domain.model.State;

public abstract class CityMixin {
	
	@JsonIgnoreProperties(value = "name", allowGetters = true)
	private State state;

}
