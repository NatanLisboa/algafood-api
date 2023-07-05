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
    public void confirm(String orderCode) {
        Order order = orderIssuanceService.findOrThrowException(orderCode);
        order.confirm();
    }

    @Transactional
    public void cancel(String orderCode) {
        Order order = orderIssuanceService.findOrThrowException(orderCode);
        order.cancel();
    }

    @Transactional
    public void deliver(String orderCode) {
        Order order = orderIssuanceService.findOrThrowException(orderCode);
        order.deliver();
    }

}
