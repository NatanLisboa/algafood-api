package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodRepository {

    List<PaymentMethod> findAll();
    PaymentMethod findById(Long id);

    PaymentMethod save(PaymentMethod paymentMethod);

    void delete(PaymentMethod paymentMethod);
    
}
