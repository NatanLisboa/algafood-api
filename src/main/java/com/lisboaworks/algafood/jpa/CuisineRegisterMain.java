package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.Cuisine;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class CuisineRegisterMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);


        CuisineRegister cuisineRegister = applicationContext.getBean(CuisineRegister.class);

        Cuisine cuisine1 = new Cuisine();
        cuisine1.setName("Brazilian");

        Cuisine cuisine2 = new Cuisine();
        cuisine2.setName("Japanese");

        cuisine1 = cuisineRegister.register(cuisine1);
        cuisine2 = cuisineRegister.register(cuisine2);

        System.out.printf("%d - %s\n", cuisine1.getId(), cuisine1.getName());
        System.out.printf("%d - %s\n", cuisine2.getId(), cuisine2.getName());

    }

}
