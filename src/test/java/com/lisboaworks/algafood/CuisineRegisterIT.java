package com.lisboaworks.algafood;

import com.lisboaworks.algafood.domain.exception.CuisineNotFoundException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.service.CuisineRegisterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class CuisineRegisterIT {

    @Autowired
    private CuisineRegisterService cuisineRegisterService;

    @Test
    public void shouldAssignId_WhenRegisterValidCuisine() {
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
    public void shouldFail_WhenRegisterCuisineWithoutName() {
        // scenario
        Cuisine cuisine = new Cuisine();
        cuisine.setName(null);

        // action
        ConstraintViolationException expectedException = Assertions.assertThrows(ConstraintViolationException.class,
                () -> cuisineRegisterService.save(new Cuisine()));

        // validation
        assertThat(expectedException).isNotNull();

    }
    
    @Test
    public void shouldFail_WhenRemoveCuisineAlreadyInUse() {
    	//scenario
    	Long cuisineId = 1L;
    	
    	//action
    	EntityAlreadyInUseException expectedException = Assertions.assertThrows(EntityAlreadyInUseException.class, 
    			() -> cuisineRegisterService.delete(cuisineId));
    	
    	//validation
    	assertThat(expectedException).isNotNull();
    }
    
    @Test
    public void shouldFail_WhenRemoveNonexistentCuisine() {
    	//scenario
    	Long cuisineId = 0L;
    	
    	//action
    	CuisineNotFoundException expectedException = Assertions.assertThrows(CuisineNotFoundException.class, 
    			() -> cuisineRegisterService.delete(cuisineId));
    	
    	//validation
    	assertThat(expectedException).isNotNull();
    }
    
    

}
