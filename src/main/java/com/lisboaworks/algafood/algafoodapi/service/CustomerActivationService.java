package com.lisboaworks.algafood.algafoodapi.service;

import com.lisboaworks.algafood.algafoodapi.model.Customer;
import com.lisboaworks.algafood.algafoodapi.notification.EmailNotifier;
import com.lisboaworks.algafood.algafoodapi.notification.Notifier;
import org.springframework.stereotype.Component;

@Component
public class CustomerActivationService {

    private final Notifier notifier;

    public CustomerActivationService(Notifier notifier) {
        this.notifier = notifier;

        System.out.println("CustomerActivationService" + notifier);
    }

    public void activate(Customer customer) {
        customer.activate();

        notifier.notify(customer, "Your system register is active!");
    }


}
