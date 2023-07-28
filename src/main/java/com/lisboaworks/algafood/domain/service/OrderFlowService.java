package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.service.EmailSendingService.Message;
import com.lisboaworks.algafood.domain.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class OrderFlowService {

    private final OrderIssuanceService orderIssuanceService;
    private final EmailSendingService emailSendingService;

    @Transactional
    public void confirm(String orderCode) {
        Order order = orderIssuanceService.findOrThrowException(orderCode);
        order.confirm();

        Message message = Message.builder()
                .subject(order.getRestaurant().getName() + " - Confirmed order")
                .body("The order with code <strong>" + order.getCode() + "</strong> was confirmed.")
                .receiver(order.getCustomer().getEmail())
                .build();

        emailSendingService.send(message);
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
