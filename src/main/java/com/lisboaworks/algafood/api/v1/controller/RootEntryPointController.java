package com.lisboaworks.algafood.api.v1.controller;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RootEntryPointController {

    private final AlgaLinks algaLinks;

    @GetMapping
    public RootEntryPointModel root() {
        RootEntryPointModel rootEntryPointModel = new RootEntryPointModel();

        rootEntryPointModel.add(algaLinks.linkToCuisines("cuisines"));
        rootEntryPointModel.add(algaLinks.linkToOrders("orders"));
        rootEntryPointModel.add(algaLinks.linkToRestaurants("restaurants"));
        rootEntryPointModel.add(algaLinks.linkToUserGroups("user-groups"));
        rootEntryPointModel.add(algaLinks.linkToUsers("users"));
        rootEntryPointModel.add(algaLinks.linkToPermissions("permissions"));
        rootEntryPointModel.add(algaLinks.linkToPaymentMethods("payment-methods"));
        rootEntryPointModel.add(algaLinks.linkToStates("states"));
        rootEntryPointModel.add(algaLinks.linkToCities("cities"));
        rootEntryPointModel.add(algaLinks.linkToStatistics("statistics"));

        return rootEntryPointModel;
    }

    private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {

    }

}
