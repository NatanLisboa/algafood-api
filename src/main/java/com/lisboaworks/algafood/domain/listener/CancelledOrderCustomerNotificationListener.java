package com.lisboaworks.algafood.domain.listener;

import com.lisboaworks.algafood.domain.event.CancelledOrderEvent;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.service.EmailSendingService;
import com.lisboaworks.algafood.domain.service.EmailSendingService.Message;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class CancelledOrderCustomerNotificationListener {

    private final EmailSendingService emailSendingService;

    @TransactionalEventListener
    public void whenCancellingOrder(CancelledOrderEvent event) {
        Order order = event.getOrder();

        Message message = Message.builder()
                .subject(order.getRestaurant().getName() + " - Cancelled order")
                .body("emails/cancelled-order.html")
                .variable("order", order)
                .receiver(order.getCustomer().getEmail())
                .build();

        emailSendingService.send(message);
    }

}
