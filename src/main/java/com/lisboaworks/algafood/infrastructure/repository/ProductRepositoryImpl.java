package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.repository.ProductRepositoryQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProductRepositoryImpl implements ProductRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    @Transactional
    public ProductPhoto save(ProductPhoto productPhoto) {
        return manager.merge(productPhoto);
    }

    @Override
    public void delete(ProductPhoto productPhoto) {
        manager.remove(productPhoto);
    }

}
