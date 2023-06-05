package com.lisboaworks.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lisboaworks.algafood.api.dto.mixin.CityMixin;
import com.lisboaworks.algafood.api.dto.mixin.CuisineMixin;
import com.lisboaworks.algafood.api.dto.mixin.RestaurantMixin;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.model.Restaurant;

@Component
@SuppressWarnings("serial")
public class JacksonMixinModule extends SimpleModule {

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurant.class, RestaurantMixin.class);
		setMixInAnnotation(City.class, CityMixin.class);
		setMixInAnnotation(Cuisine.class, CuisineMixin.class);
	}
	
}
