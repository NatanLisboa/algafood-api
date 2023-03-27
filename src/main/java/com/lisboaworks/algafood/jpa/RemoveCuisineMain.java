package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.Cuisine;
import com.lisboaworks.algafood.domain.repository.CuisineRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class RemoveCuisineMain {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);


        CuisineRepository cuisineRepository = applicationContext.getBean(CuisineRepository.class);

        Cuisine cuisine = new Cuisine();
        cuisine.setId(1L);

        cuisineRepository.delete(cuisine);

    }

}
