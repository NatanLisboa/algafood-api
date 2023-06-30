package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("from Order o join fetch o.customer join fetch o.restaurant r join fetch r.cuisine")
    List<Order> findAll();

}
