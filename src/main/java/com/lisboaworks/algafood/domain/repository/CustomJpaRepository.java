package com.lisboaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // No implementation by Spring Data JPA
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> customFindFirst();

    void detach(T entity);

}
