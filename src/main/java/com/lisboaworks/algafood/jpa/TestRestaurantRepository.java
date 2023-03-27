package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

public class TestRestaurantRepository {

    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);
        DecimalFormat formatter = new DecimalFormat("#,##0.00");

        // FIND ALL
        List<Restaurant> restaurants = restaurantRepository.findAll();
        for (Restaurant restaurant : restaurants) {
            System.out.println("Id: " + restaurant.getId());
            System.out.println("Name: " + restaurant.getName());
            System.out.println("Shipping fee: R$" + formatter.format(restaurant.getShippingFee()));
            System.out.println();
        }

        // FIND BY ID
        Restaurant restaurant1 = restaurantRepository.find(1L);
        System.out.println("Id: " + restaurant1.getId());
        System.out.println("Name: " + restaurant1.getName());
        System.out.println("Shipping fee: R$" + formatter.format(restaurant1.getShippingFee()));

        // ADD
        Restaurant restaurant3 = new Restaurant();
        restaurant3.setName("Restaurant 3");
        restaurant3.setShippingFee(new BigDecimal(8));

        restaurantRepository.save(restaurant3);

        // UPDATE
        restaurant3.setId(3L);
        restaurant3.setName("3 Restaurant");

        restaurantRepository.save(restaurant3);

        // DELETE
        restaurantRepository.remove(restaurant3);

    }

}
