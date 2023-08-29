package com.lisboaworks.algafood.client.api;

import com.lisboaworks.algafood.client.api.exception.ClientApiException;
import com.lisboaworks.algafood.client.model.RestaurantSummaryModel;
import com.lisboaworks.algafood.client.input.RestaurantInput;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestaurantClient {

    private static final String RESOURCE_PATH = "/restaurants";
    private RestTemplate restTemplate;
    private String url;

    public List<RestaurantSummaryModel> getAllRestaurants() {
        try {
            String resourceUri = url + RESOURCE_PATH;
            RestaurantSummaryModel[] restaurants = restTemplate
                    .getForObject(resourceUri, RestaurantSummaryModel[].class);

            assert restaurants != null;
            return Arrays.asList(restaurants);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

    public RestaurantModel addRestaurant(RestaurantInput restaurantInput) {
        String resourceUri = url + RESOURCE_PATH;

        try {
            return restTemplate.postForObject(resourceUri, restaurantInput, RestaurantModel.class);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

}
