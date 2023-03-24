package com.lisboaworks.algafood.algafoodapi.service;

import com.lisboaworks.algafood.algafoodapi.event.ActivatedCustomerEvent;
import com.lisboaworks.algafood.algafoodapi.model.Customer;
import com.lisboaworks.algafood.algafoodapi.notification.Notifier;
import com.lisboaworks.algafood.algafoodapi.notification.NotifierType;
import com.lisboaworks.algafood.algafoodapi.notification.UrgencyLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    public void activate(Customer customer) {
        customer.activate();

        //notifier.notify(customer, "Your system register is active!");
        eventPublisher.publishEvent(new ActivatedCustomerEvent(customer));
    }


}
