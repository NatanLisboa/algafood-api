package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Role;

import java.util.List;

public interface RoleRepository {


    List<Role> findAll();
    Role findById(Long id);
    Role save(Role role);
    void delete(Role role);

}
