package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EventRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EventServiceImpl extends BaseEntityServiceImpl<Event, UUID> implements EventService {

    public EventServiceImpl(EventRepository eventRepository) {
        super(eventRepository);
    }

}
