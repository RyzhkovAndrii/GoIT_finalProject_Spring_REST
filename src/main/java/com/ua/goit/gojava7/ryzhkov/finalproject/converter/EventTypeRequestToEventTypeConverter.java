package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventTypeRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventTypeRequestToEventTypeConverter implements Converter<EventTypeRequest, EventType> {

    @Override
    public EventType convert(EventTypeRequest eventTypeRequest) {
        EventType eventType = new EventType();
        eventType.setName(eventTypeRequest.getName());
        eventType.setHourlyRateCoefficient(eventTypeRequest.getHourlyRateCoefficient());
        return eventType;
    }

}
