package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.model.input.StateInput;
import com.lisboaworks.algafood.domain.model.State;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StateInputDisassembler {
	
	private final ModelMapper modelMapper;
	
	public State toDomainObject(StateInput stateInput) {
		return modelMapper.map(stateInput, State.class);
	}
	
	public void copyToDomainObject(StateInput stateInput, State state) {
		modelMapper.map(stateInput, state);
	}

}
