package com.lisboaworks.algafood.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {


    @Override
    public void serialize(Page<?> objectsPage, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeObjectField("content", objectsPage.getContent());
        jsonGenerator.writeNumberField("size", objectsPage.getSize());
        jsonGenerator.writeNumberField("totalElements", objectsPage.getTotalElements());
        jsonGenerator.writeNumberField("totalPages", objectsPage.getTotalPages());
        jsonGenerator.writeNumberField("pageNumber", objectsPage.getNumber());

        jsonGenerator.writeEndObject();
    }

}
