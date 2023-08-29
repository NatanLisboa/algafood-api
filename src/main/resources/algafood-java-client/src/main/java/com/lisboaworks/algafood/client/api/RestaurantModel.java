package com.lisboaworks.algafood.client.api;

import com.lisboaworks.algafood.client.model.AddressModel;
import com.lisboaworks.algafood.client.model.CuisineModel;
import lombok.Data;

@Data
public class RestaurantModel {

    private Long id;
    private String name;
    private String shippingFee;
    private CuisineModel cuisine;
    private Boolean active;
    private Boolean open;
    private AddressModel address;

}
