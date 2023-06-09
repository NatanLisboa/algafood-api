package com.lisboaworks.algafood.api.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisboaworks.algafood.api.dto.StateDTO;
import com.lisboaworks.algafood.domain.model.State;

@Component
public class StateDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public StateDTO toDTO(State state) {
		return modelMapper.map(state, StateDTO.class);
	}
	
	public List<StateDTO> toDTOList(List<State> states) {
		return states.stream()
				.map(state -> toDTO(state))
				.toList();		
	}

}
