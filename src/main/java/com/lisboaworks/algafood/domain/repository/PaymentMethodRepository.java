package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    @Query("select max(lastUpdateDatetime) from PaymentMethod")
    OffsetDateTime getLastUpdateDatetime();

}
