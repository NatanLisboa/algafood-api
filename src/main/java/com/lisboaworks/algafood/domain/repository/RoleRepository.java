package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
