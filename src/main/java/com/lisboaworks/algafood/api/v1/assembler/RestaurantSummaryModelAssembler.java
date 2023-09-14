package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.RestaurantController;
import com.lisboaworks.algafood.api.v1.model.RestaurantSummaryModel;
import com.lisboaworks.algafood.domain.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantSummaryModelAssembler extends RepresentationModelAssemblerSupport<Restaurant, RestaurantSummaryModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public RestaurantSummaryModelAssembler() {
		super(RestaurantController.class, RestaurantSummaryModel.class);
	}

	public RestaurantSummaryModel toModel(Restaurant restaurant) {
		RestaurantSummaryModel restaurantSummaryModel = this.createModelWithId(restaurant.getId(), restaurant);

		modelMapper.map(restaurant, restaurantSummaryModel);

		restaurantSummaryModel.add(algaLinks.linkToRestaurants("restaurants"));

		restaurantSummaryModel.getCuisine().add(algaLinks.linkToCuisine(restaurant.getCuisine().getId()));

		return restaurantSummaryModel;
	}

	public CollectionModel<RestaurantSummaryModel> toCollectionModel(List<Restaurant> restaurants) {
		return super.toCollectionModel(restaurants)
				.add(algaLinks.linkToRestaurants());
	}

}
