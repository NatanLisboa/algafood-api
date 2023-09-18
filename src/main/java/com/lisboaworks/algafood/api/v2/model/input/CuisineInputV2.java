package com.lisboaworks.algafood.api.v2.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CuisineInputV2 {

	@ApiModelProperty(example = "Brazilian")
	@NotBlank
	private String name;

}
