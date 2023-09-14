package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantController;
import com.lisboaworks.algafood.api.v1.model.RestaurantOnlyNameModel;
import com.lisboaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantOnlyNameModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantOnlyNameModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestaurantOnlyNameModelAssembler() {
		super(RestaurantController.class, RestaurantOnlyNameModel.class);
	}

	public RestaurantOnlyNameModel toModel(Restaurant restaurant) {
		RestaurantOnlyNameModel restaurantOnlyNameModel = this.createModelWithId(restaurant.getId(), restaurant);

		modelMapper.map(restaurant, restaurantOnlyNameModel);

		restaurantOnlyNameModel.add(algaLinks.linkToRestaurants("restaurants"));

		return restaurantOnlyNameModel;
	}

	public CollectionModel<RestaurantOnlyNameModel> toCollectionModel(List<Restaurant> restaurants) {
		return super.toCollectionModel(restaurants)
				.add(algaLinks.linkToRestaurants());
	}

}
