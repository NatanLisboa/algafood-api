package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.PaymentMethodNotFoundException;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentMethodRegisterService {

    public static final String PAYMENT_METHOD_ALREADY_IN_USE_MESSAGE = "Payment method with id %d cannot be deleted because it is already being used by other entities in database";

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private RestaurantRegisterService paymentMethodRegisterService;

    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Transactional
    public void delete(Long paymentMethodId) {

        try {
            paymentMethodRepository.deleteById(paymentMethodId);
            paymentMethodRepository.flush(); // execute the transactions made so far to the database
        } catch (EmptyResultDataAccessException e) {
            throw new PaymentMethodNotFoundException(paymentMethodId);
        } catch (DataIntegrityViolationException e) {
            throw new EntityAlreadyInUseException(String.format(PAYMENT_METHOD_ALREADY_IN_USE_MESSAGE, paymentMethodId));
        }

    }

    public PaymentMethod findOrThrowException(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId)
                .orElseThrow(() -> new PaymentMethodNotFoundException(paymentMethodId));
    }

}
