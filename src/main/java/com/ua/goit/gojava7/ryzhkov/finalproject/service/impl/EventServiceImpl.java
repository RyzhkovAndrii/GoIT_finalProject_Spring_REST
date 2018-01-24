package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EventRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event findById(UUID id) {
        return eventRepository.findOne(id);
    }

    @Override
    public Collection<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }

    @Override
    public void delete(UUID id) {
        eventRepository.delete(id);
    }

    @Override
    public void addEmployeeToEvent(Event event, Employee employee) {
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(event.getEmployees());
        newEventEmployees.add(employee);
        event.setEmployees(newEventEmployees);
        eventRepository.save(event);
    }

    @Override
    public void deleteEmployeeFromEvent(Event event, Employee employee) {
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(event.getEmployees());
        newEventEmployees.remove(employee); // todo optimized
        event.setEmployees(newEventEmployees);
        eventRepository.save(event);
    }

}
