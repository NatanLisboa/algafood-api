package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
