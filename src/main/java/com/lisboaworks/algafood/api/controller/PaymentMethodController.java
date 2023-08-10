package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.PaymentMethodDTOAssembler;
import com.lisboaworks.algafood.api.assembler.PaymentMethodInputDisassembler;
import com.lisboaworks.algafood.api.dto.PaymentMethodDTO;
import com.lisboaworks.algafood.api.dto.input.PaymentMethodInput;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.repository.PaymentMethodRepository;
import com.lisboaworks.algafood.domain.service.PaymentMethodRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment-methods")
@AllArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodRegisterService paymentMethodRegisterService;
    private final PaymentMethodDTOAssembler paymentMethodDTOAssembler;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodInputDisassembler paymentMethodInputDisassembler;

    @GetMapping
    public ResponseEntity<List<PaymentMethodDTO>> findAll(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";
        OffsetDateTime lastUpdateDateTime = paymentMethodRepository.getLastUpdateDatetime();
        if (Objects.nonNull(lastUpdateDateTime)) {
            eTag = String.valueOf(lastUpdateDateTime.toEpochSecond());
        }
        if (request.checkNotModified(eTag)) {
            return null;
        }

        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        List<PaymentMethodDTO> paymentMethodsDTO = paymentMethodDTOAssembler.toDTOList(paymentMethods);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(paymentMethodsDTO);
    }

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodDTO> findById(@PathVariable Long paymentMethodId, ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";
        OffsetDateTime lastUpdateDateTime = paymentMethodRepository.getLastUpdateDatetime();
        if (Objects.nonNull(lastUpdateDateTime)) {
            eTag = String.valueOf(lastUpdateDateTime.toEpochSecond());
        }
        if (request.checkNotModified(eTag)) {
            return null;
        }

        PaymentMethod paymentMethod = paymentMethodRegisterService.findOrThrowException(paymentMethodId);
        PaymentMethodDTO paymentMethodDTO = paymentMethodDTOAssembler.toDTO(paymentMethod);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(paymentMethodDTO);
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
