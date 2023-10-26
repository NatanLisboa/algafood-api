package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantController;
import com.lisboaworks.algafood.api.v1.model.RestaurantModel;
import com.lisboaworks.algafood.api.v1.model.RestaurantOnlyNameModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantOnlyNameModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantOnlyNameModel> {

	private final ModelMapper modelMapper;
	private final AlgaLinks algaLinks;
	private final SecurityHelper securityHelper;

	@Autowired
	public RestaurantOnlyNameModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks,
											SecurityHelper securityHelper) {
		super(RestaurantController.class, RestaurantOnlyNameModel.class);
		this.modelMapper = modelMapper;
		this.algaLinks = algaLinks;
		this.securityHelper = securityHelper;
	}

	public RestaurantOnlyNameModel toModel(Restaurant restaurant) {
		RestaurantOnlyNameModel restaurantOnlyNameModel = this.createModelWithId(restaurant.getId(), restaurant);

		modelMapper.map(restaurant, restaurantOnlyNameModel);

		if (securityHelper.canGetRestaurants()) {
			restaurantOnlyNameModel.add(algaLinks.linkToRestaurants("restaurants"));
		}

		return restaurantOnlyNameModel;
	}

	public CollectionModel<RestaurantOnlyNameModel> toCollectionModel(List<Restaurant> restaurants) {
		CollectionModel<RestaurantOnlyNameModel> restaurantsCollectionModel = super.toCollectionModel(restaurants);

		if (securityHelper.canGetRestaurants()) {
			restaurantsCollectionModel.add(algaLinks.linkToRestaurants());
		}

		return restaurantsCollectionModel;
	}

}
