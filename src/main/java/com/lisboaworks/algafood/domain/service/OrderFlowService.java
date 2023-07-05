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
            throw new BusinessRuleException(String.format("Status from order number %d cannot be changed from '%s' to '%s'. " +
                            "Only from '%s' to '%s' is accepted.",
                    order.getId(), order.getStatus().getDescription(),
                    OrderStatus.CONFIRMED.getDescription(),
                    OrderStatus.CREATED.getDescription(),
                    OrderStatus.CONFIRMED.getDescription()));
        }

        order.setStatus(OrderStatus.CONFIRMED);
        order.setConfirmationDatetime(OffsetDateTime.now());
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderIssuanceService.findOrThrowException(orderId);

        if (!order.getStatus().equals(OrderStatus.CREATED)) {
            throw new BusinessRuleException(String.format("Status from order number %d cannot be changed from '%s' to '%s'. " +
                            "Only from '%s' to '%s' is accepted.",
                    order.getId(), order.getStatus().getDescription(),
                    OrderStatus.CANCELLED.getDescription(),
                    OrderStatus.CREATED.getDescription(),
                    OrderStatus.CANCELLED.getDescription()));
        }

        order.setStatus(OrderStatus.CANCELLED);
        order.setCancellationDatetime(OffsetDateTime.now());
    }

    @Transactional
    public void deliver(Long orderId) {
        Order order = orderIssuanceService.findOrThrowException(orderId);

        if (!order.getStatus().equals(OrderStatus.CONFIRMED)) {
            throw new BusinessRuleException(String.format("Status from order number %d cannot be changed from '%s' to '%s'. " +
                            "Only from '%s' to '%s' is accepted.",
                    order.getId(), order.getStatus().getDescription(),
                    OrderStatus.DELIVERED.getDescription(),
                    OrderStatus.CONFIRMED.getDescription(),
                    OrderStatus.DELIVERED.getDescription()));
        }

        order.setStatus(OrderStatus.DELIVERED);
        order.setDeliveryDatetime(OffsetDateTime.now());
    }

}
