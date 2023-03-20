package com.lisboaworks.algafood.algafoodapi.controller;

import com.lisboaworks.algafood.algafoodapi.model.Customer;
import com.lisboaworks.algafood.algafoodapi.service.CustomerActivationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FirstController {

    private final CustomerActivationService customerActivationService;

    public FirstController(CustomerActivationService customerActivationService) {
        this.customerActivationService = customerActivationService;

        System.out.println("FirstController: " + customerActivationService);
    }

    @GetMapping
    @ResponseBody
    public String hello() {
        Customer joao = new Customer("João", "joao@xyz.com", "3499998888");

        customerActivationService.activate(joao);

        return "Hello!";
    }
}
