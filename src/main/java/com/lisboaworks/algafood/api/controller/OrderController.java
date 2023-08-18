package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.OrderDTOAssembler;
import com.lisboaworks.algafood.api.assembler.OrderInputDisassembler;
import com.lisboaworks.algafood.api.assembler.OrderSummaryDTOAssembler;
import com.lisboaworks.algafood.api.dto.OrderDTO;
import com.lisboaworks.algafood.api.dto.OrderSummaryDTO;
import com.lisboaworks.algafood.api.dto.input.OrderInput;
import com.lisboaworks.algafood.api.openapi.controller.OrderControllerOpenApi;
import com.lisboaworks.algafood.core.data.PageableTranslator;
import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.Order;
import com.lisboaworks.algafood.domain.repository.OrderRepository;
import com.lisboaworks.algafood.domain.filter.OrderFilter;
import com.lisboaworks.algafood.domain.service.OrderIssuanceService;
import com.lisboaworks.algafood.infrastructure.repository.spec.OrderSpecs;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class OrderController implements OrderControllerOpenApi {

    private final OrderDTOAssembler orderDTOAssembler;
    private final OrderIssuanceService orderIssuanceService;
    private final OrderRepository orderRepository;
    private final OrderSummaryDTOAssembler orderSummaryDTOAssembler;
    private final OrderInputDisassembler orderInputDisassembler;

    @GetMapping
    public Page<OrderSummaryDTO> findAll(OrderFilter filter, @PageableDefault(size = 5) Pageable pageable) {
        pageable = this.mapSortPropertiesNamesToMatchWithDomainModel(pageable);
        Page<Order> ordersPage = orderRepository.findAll(OrderSpecs.usingFilter(filter), pageable);
        List<OrderSummaryDTO> ordersDTO = orderSummaryDTOAssembler.toDTOList(ordersPage.getContent());
        Page<OrderSummaryDTO> ordersDTOPage = new PageImpl<>(ordersDTO, pageable, ordersPage.getTotalElements());
        return ordersDTOPage;
    }

    @GetMapping("/{orderCode}")
    public OrderDTO findById(@PathVariable String orderCode) {
        Order order = orderIssuanceService.findOrThrowException(orderCode);
        return orderDTOAssembler.toDTO(order);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO issue(@RequestBody @Valid OrderInput orderInput) {
        Order order = orderInputDisassembler.toDomainObject(orderInput);
        try {
            return orderDTOAssembler.toDTO(orderIssuanceService.issue(order));
        } catch (EntityNotFoundException e) {
            throw new BusinessRuleException(e.getMessage(), e);
        }
    }

    private Pageable mapSortPropertiesNamesToMatchWithDomainModel(Pageable pageable) {
        var mappingFromRequestToDomainModel = Map.of(
                "code", "code",
                "restaurant.name", "restaurant.name",
                "customerName", "customer.name",
                "totalValue", "totalValue"
        );
        return PageableTranslator.translate(pageable, mappingFromRequestToDomainModel);
    }

}
