package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantController;
import com.lisboaworks.algafood.api.v1.model.RestaurantSummaryModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantSummaryModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantSummaryModel> {

	private final ModelMapper modelMapper;
	private final AlgaLinks algaLinks;
	private final SecurityHelper securityHelper;

	@Autowired
	public RestaurantSummaryModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks,
										   SecurityHelper securityHelper) {
		super(RestaurantController.class, RestaurantSummaryModel.class);
		this.modelMapper = modelMapper;
		this.algaLinks = algaLinks;
		this.securityHelper = securityHelper;
	}

	public RestaurantSummaryModel toModel(Restaurant restaurant) {
		RestaurantSummaryModel restaurantSummaryModel = this.createModelWithId(restaurant.getId(), restaurant);

		modelMapper.map(restaurant, restaurantSummaryModel);

		if (securityHelper.canGetRestaurants()) {
			restaurantSummaryModel.add(algaLinks.linkToRestaurants("restaurants"));
		}

		if (securityHelper.canGetCuisines()) {
			restaurantSummaryModel.getCuisine().add(algaLinks.linkToCuisine(restaurant.getCuisine().getId()));
		}

		return restaurantSummaryModel;
	}

	public CollectionModel<RestaurantSummaryModel> toCollectionModel(List<Restaurant> restaurants) {
		CollectionModel<RestaurantSummaryModel> restaurantsCollectionModel = super.toCollectionModel(restaurants);

		if (securityHelper.canGetRestaurants()) {
			restaurantsCollectionModel.add(algaLinks.linkToRestaurants());
		}

		return restaurantsCollectionModel;
	}

}
