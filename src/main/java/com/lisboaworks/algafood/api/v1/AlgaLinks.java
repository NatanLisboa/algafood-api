package com.lisboaworks.algafood.api.v1;

import com.lisboaworks.algafood.api.v1.controller.*;
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

    public static final TemplateVariables PROJECTION_VARIABLES = new TemplateVariables(
            new TemplateVariable("projection", TemplateVariable.VariableType.REQUEST_PARAM)
    );

    public Link linkToOrders(String rel) {
        TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("customerId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("startCreationDatetime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("endCreationDatetime", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String ordersUrl = linkTo(OrderController.class).toUri().toString();

        return Link.of(UriTemplate.of(ordersUrl, PAGINATION_VARIABLES.concat(filterVariables)), rel);
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

    public Link linkToRestaurants(String rel) {
        String restaurantsUrl = linkTo(RestaurantController.class).toUri().toString();

        return Link.of(UriTemplate.of(restaurantsUrl, PROJECTION_VARIABLES), rel);
    }

    public Link linkToRestaurants() {
        return this.linkToRestaurants(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurant(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .findById(restaurantId)).withRel(rel);
    }


    public Link linkToRestaurant(Long restaurantId) {
        return this.linkToRestaurant(restaurantId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurantActivation(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .activate(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantInactivation(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .inactivate(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantOpening(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .open(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantClosure(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantController.class)
                .close(restaurantId)).withRel(rel);
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
        return linkTo(methodOn(RestaurantResponsibleUserController.class)
                .getAllResponsibleUsers(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantResponsibleUsers(Long restaurantId) {
        return this.linkToRestaurantResponsibleUsers(restaurantId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUserGroups(String rel) {
        return linkTo(UserGroupController.class).withRel(rel);
    }

    public Link linkToUserGroups() {
        return this.linkToUserGroups(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUserGroupsFromUser(Long userId, String rel) {
        return linkTo(methodOn(UserUserGroupController.class)
                .findAll(userId)).withRel(rel);
    }

    public Link linkToUserGroupsFromUser(Long userId) {
        return this.linkToUserGroupsFromUser(userId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUserGroupPermissions(Long userGroupId, String rel) {
        return linkTo(methodOn(UserGroupPermissionController.class)
                .findAll(userGroupId)).withRel(rel);
    }

    public Link linkToUserGroupPermissions(Long userGroupId) {
        return this.linkToUserGroupPermissions(userGroupId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToUserGroupPermissionAssociation(Long userGroupId, String rel) {
        return linkTo(methodOn(UserGroupPermissionController.class)
                .associate(userGroupId, null)).withRel(rel);
    }

    public Link linkToUserGroupPermissionDisassociation(Long userGroupId, Long permissionId, String rel) {
        return linkTo(methodOn(UserGroupPermissionController.class)
                .disassociate(userGroupId, permissionId)).withRel(rel);
    }

    public Link linkToAssociateUserGroupToUser(Long userId, String rel) {
        return linkTo(methodOn(UserUserGroupController.class)
                .associate(userId, null)).withRel(rel);
    }

    public Link linkToDisassociateUserGroupFromUser(Long userId, Long userGroupId, String rel) {
        return linkTo(methodOn(UserUserGroupController.class)
                .disassociate(userId, userGroupId)).withRel(rel);
    }

    public Link linkToPermissions(String rel) {
        return linkTo(PermissionController.class).withRel(rel);
    }

    public Link linkToPermissions() {
        return this.linkToPermissions(IanaLinkRelations.SELF_VALUE);
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

    public Link linkToRestaurantProducts(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
                .findAll(restaurantId, null))
                .withRel(rel);
    }

    public Link linkToRestaurantProducts(Long restaurantId) {
        return this.linkToRestaurantProducts(restaurantId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurantProduct(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductController.class)
				.findById(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToRestaurantProduct(Long restaurantId, Long productId) {
        return this.linkToRestaurantProduct(restaurantId, productId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurantProductPhoto(Long restaurantId, Long productId, String rel) {
        return linkTo(methodOn(RestaurantProductPhotoController.class)
                .getPhoto(restaurantId, productId))
                .withRel(rel);
    }

    public Link linkToRestaurantProductPhoto(Long restaurantId, Long productId) {
        return this.linkToRestaurantProductPhoto(restaurantId, productId, IanaLinkRelations.SELF_VALUE);
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

    public Link linkToCuisine(Long cuisineId, String rel) {
        return linkTo(methodOn(CuisineController.class)
                .findById(cuisineId)).withRel(rel);
    }

    public Link linkToCuisine(Long cuisineId) {
        return this.linkToCuisine(cuisineId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToPaymentMethods(String rel) {
        return linkTo(PaymentMethodController.class).withRel(rel);
    }

    public Link linkToPaymentMethods() {
        return this.linkToPaymentMethods(IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToPaymentMethod(Long paymentMethodId, String rel) {
        return linkTo(methodOn(PaymentMethodController.class)
                .findById(paymentMethodId, null)).withRel(rel);
    }

    public Link linkToRestaurantPaymentMethods(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantPaymentMethodController.class)
                .findAll(restaurantId)).withRel(rel);
    }

    public Link linkToRestaurantPaymentMethods(Long restaurantId) {
        return this.linkToRestaurantPaymentMethods(restaurantId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToRestaurantPaymentMethodDisassociation(Long restaurantId, Long paymentMethodId, String rel) {
        return linkTo(methodOn(RestaurantPaymentMethodController.class)
                .disassociate(restaurantId, paymentMethodId)).withRel(rel);
    }

    public Link linkToRestaurantPaymentMethodAssociation(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantPaymentMethodController.class)
                .associate(restaurantId, null)).withRel(rel);
    }

    public Link linkToRestaurantResponsibleUserAssociation(Long restaurantId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleUserController.class)
                .associateResponsibleUser(restaurantId, null)).withRel(rel);
    }

    public Link linkToRestaurantResponsibleUserDisassociation(Long restaurantId, Long userId, String rel) {
        return linkTo(methodOn(RestaurantResponsibleUserController.class)
                .disassociateResponsibleUser(restaurantId, userId)).withRel(rel);
    }

    public Link linkToPaymentMethod(Long paymentMethodId) {
        return this.linkToPaymentMethod(paymentMethodId, IanaLinkRelations.SELF_VALUE);
    }

    public Link linkToStatistics(String rel) {
        return linkTo(StatisticsController.class)
                .withRel(rel);
    }

    public Link linkToDailySalesStatistics(String rel) {
        final TemplateVariables filterVariables = new TemplateVariables(
                new TemplateVariable("restaurantId", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("startCreationDatetime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("endCreationDatetime", TemplateVariable.VariableType.REQUEST_PARAM),
                new TemplateVariable("timeOffset", TemplateVariable.VariableType.REQUEST_PARAM)
        );

        String dailySalesStatisticsUrl = linkTo(methodOn(StatisticsController.class)
                .getDailySales(null, null)).toUri().toString();

        return Link.of(UriTemplate.of(dailySalesStatisticsUrl, filterVariables), rel);
    }

}
