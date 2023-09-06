package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.PaymentMethodInputDisassembler;
import com.lisboaworks.algafood.api.assembler.PaymentMethodModelAssembler;
import com.lisboaworks.algafood.api.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.model.input.PaymentMethodInput;
import com.lisboaworks.algafood.api.openapi.controller.PaymentMethodControllerOpenApi;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.repository.PaymentMethodRepository;
import com.lisboaworks.algafood.domain.service.PaymentMethodRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
@RequestMapping(value = "/payment-methods", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PaymentMethodController implements PaymentMethodControllerOpenApi {

    private final PaymentMethodRegisterService paymentMethodRegisterService;
    private final PaymentMethodModelAssembler paymentMethodModelAssembler;
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodInputDisassembler paymentMethodInputDisassembler;

    @GetMapping
    public ResponseEntity<CollectionModel<PaymentMethodModel>> findAll(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        String eTag = "0";
        OffsetDateTime lastUpdateDateTime = paymentMethodRepository.getLastUpdateDatetime();
        if (Objects.nonNull(lastUpdateDateTime)) {
            eTag = String.valueOf(lastUpdateDateTime.toEpochSecond());
        }
        if (request.checkNotModified(eTag)) {
            return null;
        }

        CollectionModel<PaymentMethodModel> paymentMethodsModel = paymentMethodModelAssembler
                .toCollectionModel(paymentMethodRepository.findAll());

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(paymentMethodsModel);
    }

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodModel> findById(@PathVariable Long paymentMethodId, ServletWebRequest request) {
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
        PaymentMethodModel paymentMethodModel = paymentMethodModelAssembler.toModel(paymentMethod);

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .eTag(eTag)
                .body(paymentMethodModel);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodModel add(@Valid @RequestBody PaymentMethodInput paymentMethodInput) {
        PaymentMethod paymentMethod = paymentMethodInputDisassembler.toDomainObject(paymentMethodInput);
        return paymentMethodModelAssembler.toModel(paymentMethodRegisterService.save(paymentMethod));
    }

    @PutMapping("/{paymentMethodId}")
    public PaymentMethodModel update(@PathVariable Long paymentMethodId,
                                     @RequestBody @Valid PaymentMethodInput newPaymentMethodInput) {
        PaymentMethod paymentMethod = paymentMethodRegisterService.findOrThrowException(paymentMethodId);
        paymentMethodInputDisassembler.copyToDomainObject(newPaymentMethodInput, paymentMethod);
        return paymentMethodModelAssembler.toModel(paymentMethodRegisterService.save(paymentMethod));
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long paymentMethodId) {
        paymentMethodRegisterService.delete(paymentMethodId);
    }

}
