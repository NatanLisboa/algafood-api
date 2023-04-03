package com.lisboaworks.algafood.api.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.lisboaworks.algafood.domain.model.Cuisine;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

@JacksonXmlRootElement(localName = "cuisines")
@Data
public class CuisineXMLWrapper {

    @JacksonXmlProperty(localName = "cuisine")
    @JacksonXmlElementWrapper(useWrapping = false) // Desabilita a aparição de uma tag de embrulho List para os elementos da lista
    @NonNull
    private List<Cuisine> cuisines;

}
