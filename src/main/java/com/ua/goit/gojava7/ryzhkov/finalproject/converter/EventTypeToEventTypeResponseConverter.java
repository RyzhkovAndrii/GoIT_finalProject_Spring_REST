package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventTypeResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EventTypeToEventTypeResponseConverter implements Converter<EventType, EventTypeResponse> {

    @Override
    public EventTypeResponse convert(EventType eventType) {
        EventTypeResponse eventTypeResponse = new EventTypeResponse();
        eventTypeResponse.setId(eventType.getId());
        eventTypeResponse.setName(eventType.getName());
        eventTypeResponse.setHourlyRateCoefficient(eventType.getHourlyRateCoefficient());
        return null;
    }

}
