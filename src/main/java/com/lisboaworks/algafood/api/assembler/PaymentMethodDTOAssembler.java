package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.PaymentMethodDTO;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PaymentMethodDTOAssembler {

    private final ModelMapper modelMapper;

    public PaymentMethodDTO toDTO(PaymentMethod paymentMethod) {
        return modelMapper.map(paymentMethod, PaymentMethodDTO.class);
    }

    public List<PaymentMethodDTO> toDTOList(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(paymentMethod -> toDTO(paymentMethod))
                .toList();
    }

}
