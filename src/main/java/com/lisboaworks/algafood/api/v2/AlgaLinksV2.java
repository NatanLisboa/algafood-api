package com.lisboaworks.algafood.api.v2;

import com.lisboaworks.algafood.api.v2.controller.CityControllerV2;
import com.lisboaworks.algafood.api.v2.controller.CuisineControllerV2;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
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

    public Link linkToCuisines(String rel) {
        return linkTo(CuisineControllerV2.class)
                .withRel(rel);
    }

    public Link linkToCuisines() {
        return this.linkToCuisines(IanaLinkRelations.SELF_VALUE);
    }

}
