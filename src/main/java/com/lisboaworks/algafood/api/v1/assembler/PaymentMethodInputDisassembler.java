package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.model.input.PaymentMethodInput;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentMethodInputDisassembler {
    
    private final ModelMapper modelMapper;

    public PaymentMethod toDomainObject(PaymentMethodInput paymentMethodInput) {
        return modelMapper.map(paymentMethodInput, PaymentMethod.class);
    }

    public void copyToDomainObject(PaymentMethodInput paymentMethodInput, PaymentMethod paymentMethod) {
        modelMapper.map(paymentMethodInput, paymentMethod);
    }


}
