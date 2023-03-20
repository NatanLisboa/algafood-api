package com.lisboaworks.algafood.algafoodapi.notification;

import com.lisboaworks.algafood.algafoodapi.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifier implements Notifier {

    public EmailNotifier() {
        System.out.println("EmailNotifier");
    }

    public void notify(Customer customer, String message) {
        System.out.printf("Notifying %s by email %s: %s\n",
                customer.getName(), customer.getEmail(), message);
    }

}
