package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.model.StateModel;
import com.lisboaworks.algafood.domain.model.State;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StateModelAssembler {
	
	private final ModelMapper modelMapper;
	
	public StateModel toModel(State state) {
		return modelMapper.map(state, StateModel.class);
	}
	
	public List<StateModel> toCollectionModel(List<State> states) {
		return states.stream()
				.map(state -> toModel(state))
				.toList();		
	}

}
