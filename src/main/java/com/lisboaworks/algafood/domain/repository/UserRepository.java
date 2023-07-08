package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CustomJpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
