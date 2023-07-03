package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class OrderFlowService {

    private final OrderIssuanceService orderIssuanceService;

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderIssuanceService.findOrThrowException(orderId);

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new BusinessRuleException(String.format("Status from order number %d cannot be changed from %s to %s",
                    order.getId(), order.getStatus().getDescription(),
                    OrderStatus.CONFIRMED.getDescription()));
        }

        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmationDatetime(OffsetDateTime.now());
    }

}
