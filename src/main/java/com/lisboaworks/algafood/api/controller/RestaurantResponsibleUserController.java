package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.UserModelAssembler;
import com.lisboaworks.algafood.api.model.UserModel;
import com.lisboaworks.algafood.api.openapi.controller.RestaurantResponsibleUserControllerOpenApi;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/responsible-users")
@AllArgsConstructor
public class RestaurantResponsibleUserController implements RestaurantResponsibleUserControllerOpenApi {

    private final RestaurantRegisterService restaurantRegisterService;
    private final UserModelAssembler userModelAssembler;

    @GetMapping
    public List<UserModel> getAllResponsibleUsers(@PathVariable Long restaurantId) {
        return userModelAssembler.toCollectionModel(restaurantRegisterService.getAllResponsibleUsers(restaurantId));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associateResponsibleUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegisterService.associateResponsibleUser(restaurantId, userId);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disassociateResponsibleUser(@PathVariable Long restaurantId, @PathVariable Long userId) {
        restaurantRegisterService.disassociateResponsibleUser(restaurantId, userId);
    }

}
