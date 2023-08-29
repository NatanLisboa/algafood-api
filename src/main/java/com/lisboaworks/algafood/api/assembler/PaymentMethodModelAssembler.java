package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.PaymentMethodModel;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@AllArgsConstructor
public class PaymentMethodModelAssembler {

    private final ModelMapper modelMapper;

    public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodModel.class);
    }

    public List<PaymentMethodModel> toCollectionModel(Collection<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(this::toModel)
                .toList();
    }

}
