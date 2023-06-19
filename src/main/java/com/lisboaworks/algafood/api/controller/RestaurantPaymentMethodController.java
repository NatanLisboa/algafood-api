package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.PaymentMethodDTOAssembler;
import com.lisboaworks.algafood.api.dto.PaymentMethodDTO;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/payment-methods")
@AllArgsConstructor
public class RestaurantPaymentMethodController {

    private final RestaurantRegisterService restaurantRegisterService;
    private final PaymentMethodDTOAssembler paymentMethodDTOAssembler;

    @GetMapping
    public List<PaymentMethodDTO> findAll(@PathVariable Long restaurantId) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(restaurantId);
        return paymentMethodDTOAssembler.toDTOList(restaurant.getPaymentMethods());
    }

    @PutMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.associatePaymentMethod(restaurantId, paymentMethodId);
    }

    @DeleteMapping("/{paymentMethodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociate(@PathVariable Long restaurantId, @PathVariable Long paymentMethodId) {
        restaurantRegisterService.disassociatePaymentMethod(restaurantId, paymentMethodId);
    }

}
