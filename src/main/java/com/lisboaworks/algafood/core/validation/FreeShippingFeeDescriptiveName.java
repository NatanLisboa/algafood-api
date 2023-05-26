package com.lisboaworks.algafood.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { FreeShippingFeeDescriptiveNameValidator.class })
public @interface FreeShippingFeeDescriptiveName {

    String message() default "Invalid mandatory description";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    String valueField();

    String descriptionField();

    String mandatoryDescription();
}
