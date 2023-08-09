package com.lisboaworks.algafood.client;

import com.lisboaworks.algafood.client.api.RestaurantClient;
import com.lisboaworks.algafood.client.api.exception.ClientApiException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class RestaurantListingMain {

    public static void main(String[] args) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            RestaurantClient restaurantClient = new RestaurantClient(restTemplate, "http://api.algafood.local:8080");

            restaurantClient.getAllRestaurants()
                    .forEach(System.out::println);
        } catch (ClientApiException e) {
            if (Objects.nonNull(e.getAlgafoodApiException())) {
                System.out.println(e.getAlgafoodApiException().getUserMessage());
            } else {
                System.out.println("Unknown error");
                e.printStackTrace();
            }
        }

    }

}
