package com.lisboaworks.algafood.algafoodapi.notification;

import com.lisboaworks.algafood.algafoodapi.model.Customer;
import org.springframework.stereotype.Component;

public class EmailNotifier implements Notifier {

    private boolean upperCase;
    private final String hostServerSmtp;

    public EmailNotifier(String hostServerSmtp) {
        this.hostServerSmtp = hostServerSmtp;
        System.out.println("EmailNotifier");
    }

    public void notify(Customer customer, String message) {
        if (this.upperCase) {
            message = message.toUpperCase();
        }
        System.out.printf("Notifying %s by email %s using SMTP %s: %s\n",
                customer.getName(), customer.getEmail(), this.hostServerSmtp,  message);
    }

    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }
}
