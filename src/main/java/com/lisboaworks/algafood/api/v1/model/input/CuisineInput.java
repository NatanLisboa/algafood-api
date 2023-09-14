package com.lisboaworks.algafood.api.v1.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CuisineInput {

	@ApiModelProperty(example = "Brazilian")
	@NotBlank
	private String name;

}
