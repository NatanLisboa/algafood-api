package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Permission, Long> {

}
