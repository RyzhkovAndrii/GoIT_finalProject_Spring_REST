package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventToEventResponseConverter implements Converter<Event, EventResponse> {

    @Override
    public EventResponse convert(Event event) {
        EventResponse eventResponse = new EventResponse();
        eventResponse.setId(event.getId());
        eventResponse.setDescription(event.getDescription());
        eventResponse.setDate(event.getDate());
        eventResponse.setDuration(event.getDuration());
        eventResponse.setTypeId(event.getType().getId());
        return eventResponse;
    }

}
