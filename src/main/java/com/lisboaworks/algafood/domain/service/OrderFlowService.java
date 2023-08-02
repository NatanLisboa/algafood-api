package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.repository.OrderRepository;
import com.lisboaworks.algafood.domain.service.EmailSendingService.Message;
import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderFlowService {

    private final OrderIssuanceService orderIssuanceService;
    private final OrderRepository orderRepository;

    @Transactional
    public void confirm(String orderCode) {
        Order order = orderIssuanceService.findOrThrowException(orderCode);
        order.confirm();
        
        orderRepository.save(order);
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
