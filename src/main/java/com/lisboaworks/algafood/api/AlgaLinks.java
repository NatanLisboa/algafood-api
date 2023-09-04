package com.lisboaworks.algafood.api;

import com.lisboaworks.algafood.api.controller.*;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinks {

    public static final TemplateVariables PAGINATION_VARIABLES = new TemplateVariables(
            new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM),
            new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToOrders() {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("customerId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("startCreationDatetime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("endCreationDatetime", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String ordersUrl = linkTo(OrderController.class).toUri().toString();

        return Link.of(UriTemplate.of(ordersUrl, PAGINATION_VARIABLES.concat(filterVariables)), "orders");
    }

    public Link linkToOrderConfirmation(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .confirm(orderCode)).withRel(rel);
    }

    public Link linkToOrderDelivery(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .deliver(orderCode)).withRel(rel);
    }

    public Link linkToOrderCancellation(String orderCode, String rel) {
        return linkTo(methodOn(OrderFlowController.class)
                .cancel(orderCode)).withRel(rel);
    }

    public Link linkToRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .findById(restaurantId)).withRel(rel);
    }


    public Link linkToRestaurant(Long restaurantId) {
        return this.linkToRestaurant(restaurantId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUsers(String rel) {
        return linkTo(methodOn(UserController.class)
                .findAll()).withRel(rel);
    }

    public Link linkToUsers() {
        return this.linkToUsers(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUser(Long userId, String rel) {
        return linkTo(methodOn(UserController.class)
                .findById(userId)).withRel(rel);
    }

    public Link linkToUser(Long userId) {
        return this.linkToUser(userId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurantResponsibleUsers(Long restaurantId, String rel) {
        return linkTo(methodOn(UserController.class)
                .findById(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantResponsibleUsers(Long restaurantId) {
        return this.linkToRestaurantResponsibleUsers(restaurantId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUserGroups(Long userId, String rel) {
        return linkTo(methodOn(UserUserGroupController.class)
                .findAll(userId)).withRel(rel);
    }

    public Link linkToUserGroups(Long userId) {
        return this.linkToUserGroups(userId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCities(String rel) {
        return linkTo(methodOn(CityController.class)
                .findAll()).withRel(rel);
    }

    public Link linkToCities() {
        return this.linkToCities(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCity(Long cityId, String rel) {
        return linkTo(methodOn(CityController.class)
                .findById(cityId)).withRel(rel);
    }

    public Link linkToCity(Long cityId) {
        return this.linkToCity(cityId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToProduct(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
				.findById(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToProduct(Long restaurantId, Long productId) {
        return this.linkToProduct(restaurantId, productId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToStates(String rel) {
        return linkTo(methodOn(StateController.class)
                .findAll()).withRel(rel);
    }

    public Link linkToStates() {
        return this.linkToStates(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToState(Long stateId, String rel) {
        return linkTo(methodOn(StateController.class)
                .findById(stateId)).withRel(rel);
    }

    public Link linkToState(Long stateId) {
        return this.linkToState(stateId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToCuisines(String rel) {
        return linkTo(CuisineController.class)
                .withRel(rel);
    }

    public Link linkToCuisines() {
        return this.linkToCuisines(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToPaymentMethod(Long paymentMethodId, String rel) {
        return linkTo(methodOn(PaymentMethodController.class)
                .findById(paymentMethodId, null)).withRel(rel);
    }

    public Link linkToPaymentMethod(Long paymentMethodId) {
        return this.linkToPaymentMethod(paymentMethodId, IanaLinkRelations.SELF_VALUE);
    }

}
