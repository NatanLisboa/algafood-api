package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.OrderDTOAssembler;
import com.lisboaworks.algafood.api.assembler.OrderInputDisassembler;
import com.lisboaworks.algafood.api.assembler.OrderSummaryDTOAssembler;
import com.lisboaworks.algafood.api.dto.OrderDTO;
import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import com.lisboaworks.algafood.api.dto.input.OrderInput;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.repository.OrderRepository;
import com.lisboaworks.algafood.domain.repository.filter.OrderFilter;
import com.lisboaworks.algafood.domain.service.OrderIssuanceService;
import com.lisboaworks.algafood.infrastructure.repository.spec.OrderSpecs;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderDTOAssembler orderDTOAssembler;
    private final OrderIssuanceService orderIssuanceService;
    private final OrderRepository orderRepository;
    private final OrderSummaryDTOAssembler orderSummaryDTOAssembler;
    private final OrderInputDisassembler orderInputDisassembler;

    @GetMapping
    public List<OrderSummaryDTO> findAll(OrderFilter filter) {
        List<Order> orders = orderRepository.findAll(OrderSpecs.usingFilter(filter));
        return orderSummaryDTOAssembler.toDTOList(orders);
    }

    @GetMapping("/{orderCode}")
    public OrderDTO findById(@PathVariable String orderCode) {
        Order order = orderIssuanceService.findOrThrowException(orderCode);
        return orderDTOAssembler.toDTO(order);
    }

    @PostMapping
    public OrderDTO issue(@RequestBody @Valid OrderInput orderInput) {
        Order order = orderInputDisassembler.toDomainObject(orderInput);
        try {
            return orderDTOAssembler.toDTO(orderIssuanceService.issue(order));
        } catch (EntityNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

}
