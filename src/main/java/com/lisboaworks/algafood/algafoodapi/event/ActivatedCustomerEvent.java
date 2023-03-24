package com.lisboaworks.algafood.algafoodapi.event;

import com.lisboaworks.algafood.algafoodapi.model.Customer;

public class ActivatedCustomerEvent {

    private Customer customer;

    public ActivatedCustomerEvent(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

}
