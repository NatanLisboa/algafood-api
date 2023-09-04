package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.openapi.controller.OrderFlowControllerOpenApi;
import com.lisboaworks.algafood.domain.service.OrderFlowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/{orderCode}")
@AllArgsConstructor
public class OrderFlowController implements OrderFlowControllerOpenApi {

    private final OrderFlowService orderFlowService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirm(@PathVariable String orderCode) {
        orderFlowService.confirm(orderCode);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable String orderCode) {
        orderFlowService.cancel(orderCode);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deliver(@PathVariable String orderCode) {
        orderFlowService.deliver(orderCode);

        return ResponseEntity.noContent().build();
    }

}
