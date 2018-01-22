package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.BaseEntityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/events")
public class EventController extends BaseEntityController<Event, UUID> {

    public EventController(BaseEntityService<Event, UUID> baseEntityService) {
        super(baseEntityService);
    }

}
