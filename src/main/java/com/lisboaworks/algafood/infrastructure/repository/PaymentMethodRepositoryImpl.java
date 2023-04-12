package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PaymentMethodRepositoryImpl implements PaymentMethodRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<PaymentMethod> findAll() {
        TypedQuery<PaymentMethod> query = manager.createQuery("from PaymentMethod", PaymentMethod.class);
        return query.getResultList();
    }

    @Override
    public PaymentMethod findById(Long id) {
        return manager.find(PaymentMethod.class, id);
    }

    @Override
    @Transactional
    public PaymentMethod save(PaymentMethod paymentMethod) {
        return manager.merge(paymentMethod);
    }

    @Override
    @Transactional
    public void delete(PaymentMethod paymentMethod) {
        paymentMethod = findById(paymentMethod.getId());
        manager.remove(paymentMethod);
    }
}
