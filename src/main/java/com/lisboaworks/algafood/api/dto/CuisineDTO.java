package com.lisboaworks.algafood.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lisboaworks.algafood.api.dto.view.RestaurantView;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineDTO {

	@JsonView(RestaurantView.Summary.class)
	private Long id;

	@JsonView(RestaurantView.Summary.class)
	private String name;

}
