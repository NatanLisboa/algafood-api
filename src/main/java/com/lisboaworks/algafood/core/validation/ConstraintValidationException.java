package com.lisboaworks.algafood.core.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

@AllArgsConstructor
@Getter
public class ConstraintValidationException extends RuntimeException {

    private BindingResult bindingResult;

}
