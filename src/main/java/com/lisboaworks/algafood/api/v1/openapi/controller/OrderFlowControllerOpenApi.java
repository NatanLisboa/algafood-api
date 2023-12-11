package com.lisboaworks.algafood.api.v1.openapi.controller;

import org.springframework.http.ResponseEntity;

public interface OrderFlowControllerOpenApi {

    ResponseEntity<Void> confirm(String orderCode);

    ResponseEntity<Void> cancel(String orderCode);

    ResponseEntity<Void> deliver(String orderCode);

}
