package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.State;
import com.lisboaworks.algafood.domain.repository.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class TestStateRepositoryMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        StateRepository stateRepository = applicationContext.getBean(StateRepository.class);

        // FIND ALL
        List<State> states = stateRepository.findAll();
        for (State state : states) {
            System.out.println("Id: " + state.getId());
            System.out.println("Name: " + state.getName());
            System.out.println();
        }

        // FIND BY ID
        State state1 = stateRepository.find(1L);
        System.out.println("Id: " + state1.getId());
        System.out.println("Name: " + state1.getName());

        // ADD
        State state3 = new State();
        state3.setName("State 3");

        stateRepository.save(state3);

        // UPDATE
        state3.setId(3L);
        state3.setName("3 State");

        stateRepository.save(state3);

        // DELETE
        stateRepository.delete(state3);

        TestCityRepositoryMain.main(args);

    }
}
