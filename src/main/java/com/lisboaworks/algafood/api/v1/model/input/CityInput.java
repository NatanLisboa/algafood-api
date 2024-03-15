package com.lisboaworks.algafood.api.v1.model.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class CityInput {

	@Schema(example = "New York City")
	@NotBlank
	private String name;

    @Valid
    @NotNull
	private StateIdInput state;

}
