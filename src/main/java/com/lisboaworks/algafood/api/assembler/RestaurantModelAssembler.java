package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.CityController;
import com.lisboaworks.algafood.api.controller.RestaurantController;
import com.lisboaworks.algafood.api.model.CityModel;
import com.lisboaworks.algafood.api.model.RestaurantModel;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

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

		restaurantModel.getAddress().getCity().add(algaLinks.linkToCity(restaurant.getAddress().getCity().getId()));

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

		restaurantModel.add(algaLinks.linkToRestaurantPaymentMethods(restaurant.getId(), "payment-methods"));

		restaurantModel.add(algaLinks.linkToRestaurantResponsibleUsers(restaurant.getId(), "responsible-users"));

		return restaurantModel;
	}

	public CollectionModel<RestaurantModel> toCollectionModel(Iterable<? extends Restaurant> restaurants) {
		return super.toCollectionModel(restaurants)
				.add(algaLinks.linkToRestaurants());
	}

}
