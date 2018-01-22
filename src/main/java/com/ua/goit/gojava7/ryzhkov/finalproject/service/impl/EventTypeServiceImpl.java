package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EventTypeRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class EventTypeServiceImpl extends NamedEntityServiceImpl<EventType, UUID>
        implements EventTypeService {

    @Autowired
    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository) {
        super(eventTypeRepository);
    }

}
