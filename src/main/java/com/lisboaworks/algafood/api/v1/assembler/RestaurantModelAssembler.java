package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantController;
import com.lisboaworks.algafood.api.v1.model.RestaurantModel;
import com.lisboaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RestaurantModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestaurantModelAssembler() {
		super(RestaurantController.class, RestaurantModel.class);
	}

	public RestaurantModel toModel(Restaurant restaurant) {
		RestaurantModel restaurantModel = this.createModelWithId(restaurant.getId(), restaurant);

		modelMapper.map(restaurant, restaurantModel);

		restaurantModel.getCuisine().add(algaLinks.linkToCuisine(restaurant.getCuisine().getId()));

		if (Objects.nonNull(restaurantModel.getAddress()) && Objects.nonNull(restaurantModel.getAddress().getCity())) {
			restaurantModel.getAddress().getCity().add(algaLinks.linkToCity(restaurant.getAddress().getCity().getId()));
		}

		restaurantModel.add(algaLinks.linkToRestaurants("restaurants"));

		if (restaurant.canBeActivated()) {
			restaurantModel.add(algaLinks.linkToRestaurantActivation(restaurant.getId(), "activate"));
		}

		if (restaurant.canBeInactivated()) {
			restaurantModel.add(algaLinks.linkToRestaurantInactivation(restaurant.getId(), "inactivate"));
		}

		if (restaurant.canBeOpened()) {
			restaurantModel.add(algaLinks.linkToRestaurantOpening(restaurant.getId(), "open"));
		}

		if (restaurant.canBeClosed()) {
			restaurantModel.add(algaLinks.linkToRestaurantClosure(restaurant.getId(), "close"));
		}

		restaurantModel.add(algaLinks.linkToRestaurantProducts(restaurant.getId(), "products"));

		restaurantModel.add(algaLinks.linkToRestaurantPaymentMethods(restaurant.getId(), "payment-methods"));

		restaurantModel.add(algaLinks.linkToRestaurantResponsibleUsers(restaurant.getId(), "responsible-users"));

		return restaurantModel;
	}

	public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> restaurants) {
		return super.toCollectionModel(restaurants)
				.add(algaLinks.linkToRestaurants());
	}

}
