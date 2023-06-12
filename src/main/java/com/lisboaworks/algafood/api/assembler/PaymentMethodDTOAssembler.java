package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.PaymentMethodDTO;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentMethodDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodDTO.class);
    }

    public List<PaymentMethodDTO> toDTOList(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(paymentMethod -> toDTO(paymentMethod))
                .toList();
    }

}
