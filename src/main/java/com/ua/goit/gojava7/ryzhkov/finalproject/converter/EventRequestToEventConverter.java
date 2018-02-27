package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventRequestToEventConverter implements Converter<EventRequest, Event> {

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Override
    public Event convert(EventRequest eventRequest) {
        Event event = new Event();
        event.setDescription(eventRequest.getDescription());
        event.setDate(eventRequest.getDate());
        event.setDuration(eventRequest.getDuration());
        event.setType(eventTypeRepository.findOne(eventRequest.getTypeId())); // todo bad request exception
        return event;
    }

}
