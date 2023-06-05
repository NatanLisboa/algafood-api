package com.lisboaworks.algafood.api.dto.mixin;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lisboaworks.algafood.domain.model.Restaurant;

public abstract class CuisineMixin {
		
	@JsonIgnore
	private List<Restaurant> restaurants;
	
}
