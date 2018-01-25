package com.ua.goit.gojava7.ryzhkov.finalproject.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@JsonComponent
public class CustomEventDeserializer extends JsonDeserializer<Event> {

    private EventTypeRepository eventTypeRepository;

    @Autowired
    public CustomEventDeserializer(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public Event deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Event event = new Event();
        event.setDescription(node.get("description").asText());
        event.setDuration(node.get("duration").asDouble());
        UUID eventTypeId = UUID.fromString(node.get("type").asText());
        event.setType(eventTypeRepository.findOne(eventTypeId));
        String stringDate = node.get("date").asText();
        try {
            event.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(stringDate));
        } catch (ParseException e) {
            throw new RuntimeException(e); // todo change
        }
        return event;
    }

}
