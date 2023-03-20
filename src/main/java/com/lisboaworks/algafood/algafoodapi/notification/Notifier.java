package com.lisboaworks.algafood.algafoodapi.notification;

import com.lisboaworks.algafood.algafoodapi.model.Customer;

public interface Notifier {

    void notify(Customer customer, String message);

}
