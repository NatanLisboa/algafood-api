package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.OrderDTOAssembler;
import com.lisboaworks.algafood.api.dto.OrderDTO;
//import com.lisboaworks.algafood.api.dto.input.OrderInput;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.CityNotFoundException;
import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.repository.OrderRepository;
import com.lisboaworks.algafood.domain.service.OrderRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderDTOAssembler orderDTOAssembler;
    private final OrderRegisterService orderRegisterService;
    private final OrderRepository orderRepository;

    @GetMapping
    public List<OrderDTO> findAll() {
        return orderDTOAssembler.toDTOList(orderRepository.findAll());
    }

    @GetMapping("/{orderId}")
    public OrderDTO findById(@PathVariable Long orderId) {
        Order order = orderRegisterService.findOrThrowException(orderId);
        return orderDTOAssembler.toDTO(order);
    }
    
}
