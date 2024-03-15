package com.lisboaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;
import java.util.Objects;

public class MultipleValidator implements ConstraintValidator<Multiple, Number> {

    private int multipleNumber;

    @Override
    public void initialize(Multiple constraintAnnotation) {
        this.multipleNumber = constraintAnnotation.number();
    }

    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (Objects.nonNull(number)) {
            BigDecimal decimalValue = BigDecimal.valueOf(number.doubleValue());
            BigDecimal decimalMultiple = BigDecimal.valueOf(this.multipleNumber);
            BigDecimal rest = decimalValue.remainder(decimalMultiple);

            isValid = BigDecimal.ZERO.compareTo(rest) == 0;
        }

        return isValid;
    }
}
