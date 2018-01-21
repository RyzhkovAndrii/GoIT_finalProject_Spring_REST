package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "event-types")
public class EventTypeController extends NamedEntityController<EventType, UUID> {

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        super(eventTypeService);
    }

}
