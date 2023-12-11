package com.lisboaworks.algafood.api.v1.openapi.controller;

import com.lisboaworks.algafood.api.v1.model.UserModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

public interface RestaurantResponsibleUserControllerOpenApi {

    CollectionModel<UserModel> getAllResponsibleUsers(Long restaurantId);

    ResponseEntity<Void> associateResponsibleUser(Long restaurantId, Long userId);

    ResponseEntity<Void> disassociateResponsibleUser(Long restaurantId, Long userId);

}
