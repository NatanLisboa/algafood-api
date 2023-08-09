package com.lisboaworks.algafood.client;

import com.lisboaworks.algafood.client.api.RestaurantClient;
import com.lisboaworks.algafood.client.api.RestaurantDTO;
import com.lisboaworks.algafood.client.api.exception.AlgafoodApiException.Object;
import com.lisboaworks.algafood.client.api.exception.ClientApiException;
import com.lisboaworks.algafood.client.input.AddressInput;
import com.lisboaworks.algafood.client.input.CityIdInput;
import com.lisboaworks.algafood.client.input.CuisineIdInput;
import com.lisboaworks.algafood.client.input.RestaurantInput;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Objects;

public class RestaurantRegisteringMain {

    public static void main(String[] args) {
        CuisineIdInput cuisine = new CuisineIdInput();
        cuisine.setId(3L);

        CityIdInput city = new CityIdInput();
        city.setId(4L);

        AddressInput address = new AddressInput();
        address.setZipCode("38400-999");
        address.setStreetName("St. João Pinheiro");
        address.setNumber("1002");
        address.setComplement("A");
        address.setDistrict("Centre");
        address.setCity(city);

        RestaurantInput restaurant = new RestaurantInput();
        restaurant.setName("Brazilian Real Cuisine");
        restaurant.setShippingFee(new BigDecimal(10));
        restaurant.setCuisine(cuisine);
        restaurant.setAddress(address);

        RestaurantClient restaurantClient = new RestaurantClient(new RestTemplate(), "http://api.algafood.local:8080");

        try {
            RestaurantDTO savedRestaurant = restaurantClient.addRestaurant(restaurant);
            System.out.println(savedRestaurant);
        } catch (ClientApiException e) {
            if (Objects.nonNull(e.getAlgafoodApiException())) {
                System.out.println(e.getAlgafoodApiException().getUserMessage());
                for (Object errorObject : e.getAlgafoodApiException().getObjects()) {
                    System.out.println("- " + errorObject.getUserMessage());
                }
            } else {
                System.out.println("Unknown error");
                e.printStackTrace();
            }
        }
    }

}
