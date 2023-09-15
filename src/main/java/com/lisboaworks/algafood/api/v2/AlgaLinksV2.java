package com.lisboaworks.algafood.api.v2;

import com.lisboaworks.algafood.api.v1.controller.*;
import com.lisboaworks.algafood.api.v2.controller.CityControllerV2;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgaLinksV2 {

    public Link linkToCities(String rel) {
        return linkTo(methodOn(CityControllerV2.class)
                .findAll()).withRel(rel);
    }

    public Link linkToCities() {
        return this.linkToCities(IanaLinkRelations.SELF_VALUE);
    }

}
