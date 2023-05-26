package com.lisboaworks.algafood.core.validation;

import org.springframework.beans.BeanUtils;

import javax.validation.*;
import java.math.BigDecimal;
import java.util.Objects;

public class FreeShippingFeeDescriptiveNameValidator implements ConstraintValidator<FreeShippingFeeDescriptiveName, Object> {

    private String valueField;
    private String descriptionField;
    private String mandatoryDescription;

    @Override
    public void initialize(FreeShippingFeeDescriptiveName constraintAnnotation) {
        this.valueField = constraintAnnotation.valueField();
        this.descriptionField = constraintAnnotation.descriptionField();
        this.mandatoryDescription = constraintAnnotation.mandatoryDescription();
    }

    @Override
    public boolean isValid(Object validationObject, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        try{
            BigDecimal value = (BigDecimal) BeanUtils.getPropertyDescriptor(validationObject.getClass(), valueField)
                    .getReadMethod().invoke(validationObject);

            String description = (String) BeanUtils.getPropertyDescriptor(validationObject.getClass(), descriptionField)
                    .getReadMethod().invoke(validationObject);

            if (Objects.nonNull(value) && BigDecimal.ZERO.compareTo(value) == 0 && Objects.nonNull(description)) {
                isValid = description.toLowerCase().contains(this.mandatoryDescription.toLowerCase());
            }
        } catch (Exception e) {
            throw new ValidationException(e);
        }

        return isValid;

    }
}
