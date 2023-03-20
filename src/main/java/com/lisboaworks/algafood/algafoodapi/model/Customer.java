package com.lisboaworks.algafood.algafoodapi.model;

public class Customer {

    private final String name;
    private final String email;
    private final String phone;

    private boolean active = false;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        this.active = true;
    }
}
