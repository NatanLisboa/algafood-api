package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class TestCityRepositoryMain {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository cityRepository = applicationContext.getBean(CityRepository.class);

        // FIND ALL
        List<City> cities = cityRepository.findAll();
        for (City city : cities) {
            System.out.println("Id: " + city.getId());
            System.out.println("Name: " + city.getName());
            System.out.println();
        }

        // FIND BY ID
        City city1 = cityRepository.find(1L);
        System.out.println("Id: " + city1.getId());
        System.out.println("Name: " + city1.getName());
        
        // ADD
        State state = new State();
        state.setId(2L);

        City city3 = new City();
        city3.setName("City 3");
        city3.setState(state);

        cityRepository.save(city3);

        // UPDATE
        city3.setId(3L);
        city3.setName("3 City");

        cityRepository.save(city3);

        // DELETE
        cityRepository.delete(city3);

    }
}
