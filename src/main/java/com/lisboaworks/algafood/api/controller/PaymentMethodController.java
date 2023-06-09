package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.PaymentMethodDTOAssembler;
import com.lisboaworks.algafood.api.assembler.PaymentMethodInputDisassembler;
import com.lisboaworks.algafood.api.dto.PaymentMethodDTO;
import com.lisboaworks.algafood.api.dto.input.PaymentMethodInput;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.repository.PaymentMethodRepository;
import com.lisboaworks.algafood.domain.service.PaymentMethodRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payment-methods")
@AllArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodRegisterService paymentMethodRegisterService;
    private final PaymentMethodDTOAssembler paymentMethodDTOAssembler;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodInputDisassembler paymentMethodInputDisassembler;

    @GetMapping
    public List<PaymentMethodDTO> findAll() {
        return paymentMethodDTOAssembler.toDTOList(paymentMethodRepository.findAll());
    }

    @GetMapping("/{paymentMethodId}")
    public PaymentMethodDTO findById(@PathVariable Long paymentMethodId) {
        PaymentMethod paymentMethod = paymentMethodRegisterService.findOrThrowException(paymentMethodId);
        return paymentMethodDTOAssembler.toDTO(paymentMethod);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodDTO add(@Valid @RequestBody PaymentMethodInput paymentMethodInput) {
        PaymentMethod paymentMethod = paymentMethodInputDisassembler.toDomainObject(paymentMethodInput);
        return paymentMethodDTOAssembler.toDTO(paymentMethodRegisterService.save(paymentMethod));
    }

    @PutMapping("/{paymentMethodId}")
    public PaymentMethodDTO update(@PathVariable Long paymentMethodId,
                             @RequestBody @Valid PaymentMethodInput newPaymentMethodInput) {
        PaymentMethod paymentMethod = paymentMethodRegisterService.findOrThrowException(paymentMethodId);
        paymentMethodInputDisassembler.copyToDomainObject(newPaymentMethodInput, paymentMethod);
        return paymentMethodDTOAssembler.toDTO(paymentMethodRegisterService.save(paymentMethod));
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long paymentMethodId) {
        paymentMethodRegisterService.delete(paymentMethodId);
    }

}
