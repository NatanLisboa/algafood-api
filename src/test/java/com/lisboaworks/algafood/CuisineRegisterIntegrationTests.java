package com.lisboaworks.algafood;

import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CuisineRegisterIntegrationTests {

    @Autowired
    private CuisineRegisterService cuisineRegisterService;

    @Test
    public void testCuisineRegisterWithSuccess() {
        // scenario
        Cuisine cuisine = new Cuisine();
        cuisine.setName("Chinese");

        // action
        cuisine = cuisineRegisterService.save(cuisine);

        // validation
        assertThat(cuisine).isNotNull();
        assertThat(cuisine.getId()).isNotNull();
    }

    @Test
    public void testCuisineRegisterWithoutName() {
        // scenario
        Cuisine cuisine = new Cuisine();
        cuisine.setName(null);

        // action
        ConstraintViolationException expectedException = Assertions.assertThrows(ConstraintViolationException.class,
                () -> cuisineRegisterService.save(new Cuisine()));

        // validation
        assertThat(expectedException).isNotNull();

    }

}
