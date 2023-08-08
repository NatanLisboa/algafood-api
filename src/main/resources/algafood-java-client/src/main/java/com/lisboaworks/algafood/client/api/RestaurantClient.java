package com.lisboaworks.algafood.client.api;

import com.lisboaworks.algafood.client.dto.RestaurantSummaryDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class RestaurantClient {

    private static final String RESOURCE_PATH = "/restaurants";
    private RestTemplate restTemplate;
    private String url;

    public List<RestaurantSummaryDTO> getAllRestaurants() {
        String resourceUri = url + RESOURCE_PATH;
        RestaurantSummaryDTO[] restaurants = restTemplate
                .getForObject(resourceUri, RestaurantSummaryDTO[].class);

        assert restaurants != null;
        return Arrays.asList(restaurants);
    }

}
