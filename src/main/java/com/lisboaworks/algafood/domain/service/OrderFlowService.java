package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderFlowService {

    private final OrderIssuanceService orderIssuanceService;

    @Transactional
    public void confirm(Long orderId) {
        Order order = orderIssuanceService.findOrThrowException(orderId);
        order.confirm();
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderIssuanceService.findOrThrowException(orderId);
        order.cancel();
    }

    @Transactional
    public void deliver(Long orderId) {
        Order order = orderIssuanceService.findOrThrowException(orderId);
        order.deliver();
    }

}
