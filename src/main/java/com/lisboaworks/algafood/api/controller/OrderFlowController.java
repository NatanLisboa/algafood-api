package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.domain.service.OrderFlowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/{orderId}")
@AllArgsConstructor
public class OrderFlowController {

    private final OrderFlowService orderFlowService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable Long orderId) {
        orderFlowService.confirm(orderId);
    }

}
