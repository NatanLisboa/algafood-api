package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByCode(String code);

    @Query("from Order o join fetch o.customer join fetch o.restaurant r join fetch r.cuisine")
    List<Order> findAll();

}
