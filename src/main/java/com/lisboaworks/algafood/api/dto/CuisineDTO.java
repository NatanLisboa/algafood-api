package com.lisboaworks.algafood.api.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.lisboaworks.algafood.api.dto.view.RestaurantView;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuisineDTO {

	@ApiModelProperty(example = "1")
	@JsonView(RestaurantView.Summary.class)
	private Long id;

	@ApiModelProperty(example = "Thai")
	@JsonView(RestaurantView.Summary.class)
	private String name;

}
