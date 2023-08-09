package com.lisboaworks.algafood.client.api;

import com.lisboaworks.algafood.client.api.exception.ClientApiException;
import com.lisboaworks.algafood.client.dto.RestaurantSummaryDTO;
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

    public List<RestaurantSummaryDTO> getAllRestaurants() {
        try {
            String resourceUri = url + RESOURCE_PATH;
            RestaurantSummaryDTO[] restaurants = restTemplate
                    .getForObject(resourceUri, RestaurantSummaryDTO[].class);

            assert restaurants != null;
            return Arrays.asList(restaurants);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

    public RestaurantDTO addRestaurant(RestaurantInput restaurantInput) {
        String resourceUri = url + RESOURCE_PATH;

        try {
            return restTemplate.postForObject(resourceUri, restaurantInput, RestaurantDTO.class);
        } catch (RestClientResponseException e) {
            throw new ClientApiException(e.getMessage(), e);
        }
    }

}
