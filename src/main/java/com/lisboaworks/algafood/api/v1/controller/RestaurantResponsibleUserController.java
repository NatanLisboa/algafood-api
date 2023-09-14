package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.assembler.UserModelAssembler;
import com.lisboaworks.algafood.api.v1.model.UserModel;
import com.lisboaworks.algafood.api.v1.openapi.controller.RestaurantResponsibleUserControllerOpenApi;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/responsible-users")
@AllArgsConstructor
public class RestaurantResponsibleUserController implements RestaurantResponsibleUserControllerOpenApi {

    private final RestaurantRegisterService restaurantRegisterService;
    private final UserModelAssembler userModelAssembler;
    private final AlgaLinks algaLinks;
    @GetMapping
    public CollectionModel<UserModel> getAllResponsibleUsers(@PathVariable Long restaurantId) {
        CollectionModel<UserModel> responsibleUsersModel =
                userModelAssembler.toCollectionModel(restaurantRegisterService.getAllResponsibleUsers(restaurantId))
                .removeLinks()
                .add(algaLinks.linkToRestaurantResponsibleUsers(restaurantId))
                .add(algaLinks.linkToRestaurantResponsibleUserAssociation(restaurantId, "associate"));

        responsibleUsersModel.getContent().forEach(responsibleUserModel ->
                responsibleUserModel.add(algaLinks.linkToRestaurantResponsibleUserDisassociation(restaurantId,
                responsibleUserModel.getId(), "diassociate")));

        return responsibleUsersModel;
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> associateResponsibleUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegisterService.associateResponsibleUser(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> disassociateResponsibleUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegisterService.disassociateResponsibleUser(restaurantId, userId);

        return ResponseEntity.noContent().build();
    }

}
