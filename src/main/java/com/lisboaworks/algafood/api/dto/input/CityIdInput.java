package com.lisboaworks.algafood.api.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CityIdInput {

	@ApiModelProperty(example = "1")
	@NotNull
	private Long id;

}
