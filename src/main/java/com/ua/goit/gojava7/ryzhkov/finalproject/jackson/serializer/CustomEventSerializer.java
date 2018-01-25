package com.ua.goit.gojava7.ryzhkov.finalproject.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class CustomEventSerializer extends JsonSerializer<Event> {

    @Override
    public void serialize(
            Event event, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", event.getId().toString());
        jsonGenerator.writeStringField("description", event.getDescription());
        jsonGenerator.writeStringField("date", event.getDate().toString());
        jsonGenerator.writeNumberField("duration", event.getDuration());
        jsonGenerator.writeStringField("event-type", event.getType().getId().toString());
        jsonGenerator.writeEndObject();
    }

}
