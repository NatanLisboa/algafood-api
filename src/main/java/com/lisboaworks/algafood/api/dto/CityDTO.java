package com.lisboaworks.algafood.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "New York City")
	private String name;
	private StateDTO state;

}
