package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.OrderDTOAssembler;
import com.lisboaworks.algafood.api.assembler.OrderSummaryDTOAssembler;
import com.lisboaworks.algafood.api.dto.OrderDTO;
import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.repository.OrderRepository;
import com.lisboaworks.algafood.domain.service.OrderRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderDTOAssembler orderDTOAssembler;
    private final OrderRegisterService orderRegisterService;
    private final OrderRepository orderRepository;
    private final OrderSummaryDTOAssembler orderSummaryDTOAssembler;

    @GetMapping
    public List<OrderSummaryDTO> findAll() {
        return orderSummaryDTOAssembler.toDTOList(orderRepository.findAll());
    }

    @GetMapping("/{orderId}")
    public OrderDTO findById(@PathVariable Long orderId) {
        Order order = orderRegisterService.findOrThrowException(orderId);
        return orderDTOAssembler.toDTO(order);
    }
    
}
