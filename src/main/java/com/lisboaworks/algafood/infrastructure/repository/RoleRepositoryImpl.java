package com.lisboaworks.algafood.infrastructure.repository;

import com.lisboaworks.algafood.domain.model.Role;
import com.lisboaworks.algafood.domain.repository.RoleRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query = manager.createQuery("from Role", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findById(Long id) {
        return manager.find(Role.class, id);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return manager.merge(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        role = findById(role.getId());
        manager.remove(role);
    }
}
