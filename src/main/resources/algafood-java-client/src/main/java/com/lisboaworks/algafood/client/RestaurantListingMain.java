package com.lisboaworks.algafood.client;

import com.lisboaworks.algafood.client.api.RestaurantClient;
import org.springframework.web.client.RestTemplate;

public class RestaurantListingMain {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        RestaurantClient restaurantClient = new RestaurantClient(restTemplate, "http://api.algafood.local:8080");

        restaurantClient.getAllRestaurants()
                .forEach(System.out::println);
    }

}
