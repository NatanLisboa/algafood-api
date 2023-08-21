package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.openapi.controller.OrderFlowControllerOpenApi;
import com.lisboaworks.algafood.domain.service.OrderFlowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/{orderCode}")
@AllArgsConstructor
public class OrderFlowController implements OrderFlowControllerOpenApi {

    private final OrderFlowService orderFlowService;

    @PutMapping("/confirmation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirm(@PathVariable String orderCode) {
        orderFlowService.confirm(orderCode);
    }

    @PutMapping("/cancellation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable String orderCode) {
        orderFlowService.cancel(orderCode);
    }

    @PutMapping("/delivery")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliver(@PathVariable String orderCode) {
        orderFlowService.deliver(orderCode);
    }

}
