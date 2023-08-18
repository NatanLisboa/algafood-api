package com.lisboaworks.algafood.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitySummaryDTO {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "New York City")
	private String name;

	@ApiModelProperty(example = "New York")
	private String state;

}
