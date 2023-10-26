package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.StateController;
import com.lisboaworks.algafood.api.v1.model.StateModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class StateModelAssembler extends RepresentationModelAssemblerSupport<State, StateModel> {

	private final ModelMapper modelMapper;
	private final AlgaLinks algaLinks;
	private final SecurityHelper securityHelper;

	@Autowired
	public StateModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
		super(StateController.class, StateModel.class);
		this.modelMapper = modelMapper;
		this.algaLinks = algaLinks;
		this.securityHelper = securityHelper;
	}

	public StateModel toModel(State state) {
		StateModel stateModel = this.createModelWithId(state.getId(), state);

		modelMapper.map(state, stateModel);

		if (securityHelper.canGetStates()) {
			stateModel.add(algaLinks.linkToStates("states"));
		}

		return stateModel;
	}

	public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> states) {
		CollectionModel<StateModel> statesCollectionModel = super.toCollectionModel(states);

		if (securityHelper.canGetStates()) {
			statesCollectionModel.add(linkTo(StateController.class).withSelfRel());
		}

		return statesCollectionModel;
	}

}
