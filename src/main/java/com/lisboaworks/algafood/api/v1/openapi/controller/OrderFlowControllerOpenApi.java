package com.lisboaworks.algafood.api.v1.openapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

@SecurityRequirement(name = "security_auth")
public interface OrderFlowControllerOpenApi {

    ResponseEntity<Void> confirm(String orderCode);

    ResponseEntity<Void> cancel(String orderCode);

    ResponseEntity<Void> deliver(String orderCode);

}
