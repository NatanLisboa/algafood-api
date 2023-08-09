package com.lisboaworks.algafood.client.api;

import com.lisboaworks.algafood.client.dto.AddressDTO;
import com.lisboaworks.algafood.client.dto.CuisineDTO;
import lombok.Data;

@Data
public class RestaurantDTO {

    private Long id;
    private String name;
    private String shippingFee;
    private CuisineDTO cuisine;
    private Boolean active;
    private Boolean open;
    private AddressDTO address;

}
