package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.Cuisine;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class CuisineQueryMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CuisineRegister cuisineRegister = applicationContext.getBean(CuisineRegister.class);
        List<Cuisine> cuisineList = cuisineRegister.getAll();

        for (Cuisine cuisine : cuisineList) {
            System.out.println(cuisine.getName());
        }
    }
}
