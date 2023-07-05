package com.lisboaworks.algafood.domain.service;

import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.OrderNotFoundException;
import com.lisboaworks.algafood.domain.exception.ProductNotFoundException;
import com.lisboaworks.algafood.domain.model.*;
import com.lisboaworks.algafood.domain.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class OrderIssuanceService {
    private final OrderRepository orderRepository;
    private final RestaurantRegisterService restaurantRegisterService;
    private final PaymentMethodRegisterService paymentMethodRegisterService;
    private final CityRegisterService cityRegisterService;
    private final UserRegisterService userRegisterService;
    private final ProductRegisterService productRegisterService;

    @Transactional
    public Order issue(Order order) {

        //TODO: Get authenticated customer
        this.mockCustomer(order);

        this.validateOrder(order);
        this.validateOrderItems(order);

        order.setSubtotal(order.calculateSubtotal());
        order.setShippingFee(order.getRestaurant().getShippingFee());
        order.setTotalValue(order.calculateTotalValue());

        return orderRepository.save(order);

    }

    private void validateOrderItems(Order order) {
        Restaurant restaurant = order.getRestaurant();
        for (OrderItem orderItem : order.getItems()) {
            Product product = productRegisterService.findOrThrowException(restaurant.getId(), orderItem.getProduct().getId());
            orderItem.setProduct(product);
            orderItem.setUnitPrice(product.getPrice());
            orderItem.setTotalPrice(orderItem.getUnitPrice().multiply(new BigDecimal(orderItem.getQuantity())));
            orderItem.setOrder(order);
        }
    }

    private void validateOrder(Order order) {
        Restaurant restaurant = restaurantRegisterService.findOrThrowException(order.getRestaurant().getId());
        PaymentMethod paymentMethod = paymentMethodRegisterService.findOrThrowException(order.getPaymentMethod().getId());
        City city = cityRegisterService.findOrThrowException(order.getDeliveryAddress().getCity().getId());
        User customer = userRegisterService.findOrThrowException(order.getCustomer().getId());

        order.setRestaurant(restaurant);
        order.setPaymentMethod(paymentMethod);
        order.getDeliveryAddress().setCity(city);
        order.setCustomer(customer);

        if (!restaurant.hasPaymentMethod(paymentMethod)) {
            throw new BusinessRuleException(String.format("Payment method '%s' is not accepted by this restaurant", paymentMethod.getDescription()));
        }
    }

    private void mockCustomer(Order order) {
        User customer = new User();
        customer.setId(1L);
        order.setCustomer(customer);
    }

    public Order findOrThrowException(String orderCode) {
        return orderRepository.findByCode(orderCode)
                .orElseThrow(() -> new OrderNotFoundException(orderCode));
    }
}
