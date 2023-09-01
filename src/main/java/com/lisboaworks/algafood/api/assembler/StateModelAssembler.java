package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.StateController;
import com.lisboaworks.algafood.api.model.StateModel;
import com.lisboaworks.algafood.domain.model.State;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StateModelAssembler extends RepresentationModelAssemblerSupport<State, StateModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AlgaLinks algaLinks;

	public StateModelAssembler() {
		super(StateController.class, StateModel.class);
	}

	public StateModel toModel(State state) {
		StateModel stateModel = this.createModelWithId(state.getId(), state);

		modelMapper.map(state, stateModel);

		stateModel.add(algaLinks.linkToStates("states"));

		return stateModel;
	}

	public CollectionModel<StateModel> toCollectionModel(Iterable<? extends State> states) {
		return super.toCollectionModel(states)
				.add(linkTo(StateController.class).withSelfRel());
	}

}
