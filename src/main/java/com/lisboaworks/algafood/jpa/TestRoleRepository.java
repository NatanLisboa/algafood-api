package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.Role;
import com.lisboaworks.algafood.domain.repository.RoleRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class TestRoleRepository {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RoleRepository roleRepository = applicationContext.getBean(RoleRepository.class);

        // FIND ALL
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles) {
            System.out.println("Id: " + role.getId());
            System.out.println("Name: " + role.getName());
            System.out.println("Description: " + role.getDescription());
            System.out.println();
        }

        // FIND BY ID
        Role role1 = roleRepository.findById(1L);
        System.out.println("Id: " + role1.getId());
        System.out.println("Name: " + role1.getName());
        System.out.println("Description: " + role1.getDescription());


        // ADD
        Role role3 = new Role();
        role3.setName("ROOT");
        role3.setDescription("Total permission");

        roleRepository.save(role3);

        // UPDATE
        role3.setId(3L);
        role3.setName("3 Role");

        roleRepository.save(role3);

        // DELETE
        roleRepository.delete(role3);

    }
}
