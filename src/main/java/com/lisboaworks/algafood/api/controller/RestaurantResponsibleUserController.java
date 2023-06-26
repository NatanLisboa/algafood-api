package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.UserDTOAssembler;
import com.lisboaworks.algafood.api.dto.UserDTO;
import com.lisboaworks.algafood.domain.service.RestaurantRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/responsible-users")
@AllArgsConstructor
public class RestaurantResponsibleUserController {

    private final RestaurantRegisterService restaurantRegisterService;
    private final UserDTOAssembler userDTOAssembler;

    @GetMapping
    public List<UserDTO> getAllResponsibleUsers(@PathVariable Long restaurantId) {
        return userDTOAssembler.toDTOList(restaurantRegisterService.getAllResponsibleUsers(restaurantId));
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
