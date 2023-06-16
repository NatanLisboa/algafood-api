package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.dto.StateDTO;
import com.lisboaworks.algafood.domain.model.State;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StateDTOAssembler {
	
	private final ModelMapper modelMapper;
	
	public StateDTO toDTO(State state) {
		return modelMapper.map(state, StateDTO.class);
	}
	
	public List<StateDTO> toDTOList(List<State> states) {
		return states.stream()
				.map(state -> toDTO(state))
				.toList();		
	}

}
