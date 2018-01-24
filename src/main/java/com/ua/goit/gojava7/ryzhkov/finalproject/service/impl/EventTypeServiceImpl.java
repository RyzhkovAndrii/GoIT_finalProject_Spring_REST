package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EventTypeRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@Transactional
public class EventTypeServiceImpl implements EventTypeService {

    private EventTypeRepository eventTypeRepository;

    @Autowired
    public EventTypeServiceImpl(EventTypeRepository eventTypeRepository) {
        this.eventTypeRepository = eventTypeRepository;
    }

    @Override
    public EventType findById(UUID id) {
        return eventTypeRepository.findOne(id);
    }

    @Override
    public Collection<EventType> findAll() {
        return eventTypeRepository.findAll();
    }

    @Override
    public EventType save(EventType eventType) {
        return eventTypeRepository.save(eventType);
    }

    @Override
    public void delete(EventType eventType) {
        eventTypeRepository.delete(eventType);
    }

    @Override
    public void delete(UUID id) {
        eventTypeRepository.delete(id);
    }

    @Override
    public EventType findByName(String name) {
        return eventTypeRepository.findByName(name);
    }
    
}
