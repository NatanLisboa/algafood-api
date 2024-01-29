package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RootEntryPointController {

    private final AlgaLinks algaLinks;
    private final SecurityHelper securityHelper;

    @GetMapping
    @Operation(hidden = true)
    public RootEntryPointModel root() {
        RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();

        if (securityHelper.canGetCuisines()) {
            rootEntryPointModel.add(algaLinks.linkToCuisines("cuisines"));
        }

        if (securityHelper.canGetAllOrders()) {
            rootEntryPointModel.add(algaLinks.linkToOrders("orders"));
        }

        if (securityHelper.canGetRestaurants()) {
            rootEntryPointModel.add(algaLinks.linkToRestaurants("restaurants"));
        }

        if (securityHelper.canGetUsersUserGroupsAndPermissions()) {
            rootEntryPointModel.add(algaLinks.linkToUserGroups("user-groups"));
            rootEntryPointModel.add(algaLinks.linkToUsers("users"));
            rootEntryPointModel.add(algaLinks.linkToPermissions("permissions"));
        }

        if (securityHelper.canGetPaymentMethods()) {
            rootEntryPointModel.add(algaLinks.linkToPaymentMethods("payment-methods"));
        }

        if (securityHelper.canGetStates()) {
            rootEntryPointModel.add(algaLinks.linkToStates("states"));
        }

        if (securityHelper.canGetCities()) {
            rootEntryPointModel.add(algaLinks.linkToCities("cities"));
        }

        if (securityHelper.canGetStatistics()) {
            rootEntryPointModel.add(algaLinks.linkToStatistics("statistics"));
        }

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

}
