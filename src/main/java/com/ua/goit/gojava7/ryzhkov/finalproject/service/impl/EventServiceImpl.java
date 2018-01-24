package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeRepository;
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

    private EmployeeRepository employeeRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EmployeeRepository employeeRepository) {
        this.eventRepository = eventRepository;
        this.employeeRepository = employeeRepository;
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
    public void addEmployeeToEvent(UUID eventId, UUID employeeId) {
        Event event = eventRepository.findOne(eventId);
        Employee employee = employeeRepository.findOne(employeeId);
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(event.getEmployees());
        newEventEmployees.add(employee);
        event.setEmployees(newEventEmployees);
        eventRepository.save(event);
    }

    @Override
    public void deleteEmployeeFromEvent(UUID eventId, UUID employeeId) {
        Event event = eventRepository.findOne(eventId);
        Collection<Employee> eventEmployees = event.getEmployees();
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(eventEmployees);
        newEventEmployees.removeIf(role -> role.getId().equals(employeeId));
        event.setEmployees(newEventEmployees);
        eventRepository.save(event);
    }

}
