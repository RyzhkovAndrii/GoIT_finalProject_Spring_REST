package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EventRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
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

    private EmployeeService employeeService;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, EmployeeService employeeService) {
        this.eventRepository = eventRepository;
        this.employeeService = employeeService;
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
    public void update(Event event) {
        Event previousEvent = eventRepository.getOne(event.getId());
        double durationDifference = event.getDuration() - previousEvent.getDuration();
        if (durationDifference != 0) {
            for (Employee employee : event.getEmployees()) {
                employeeService.changeCurrentWorkingHours(employee, durationDifference);
            }
        }
        save(event);
    }

    @Override
    public void addEmployeeToEvent(UUID eventId, UUID employeeId) {
        Event event = findById(eventId);
        Employee employee = employeeService.findById(employeeId);
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(event.getEmployees());
        newEventEmployees.add(employee);
        event.setEmployees(newEventEmployees);
        eventRepository.save(event);
        double workingHours = event.getDuration() * event.getType().getHourlyRateCoefficient();
        employeeService.changeCurrentWorkingHours(employee, workingHours);
    }

    @Override
    public void deleteEmployeeFromEvent(UUID eventId, UUID employeeId) {
        Event event = findById(eventId);
        Employee employee = employeeService.findById(employeeId);
        Collection<Employee> eventEmployees = event.getEmployees();
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(eventEmployees);
        newEventEmployees.remove(employee);
        event.setEmployees(newEventEmployees);
        eventRepository.save(event);
        double workingHours = (-1) * event.getDuration() * event.getType().getHourlyRateCoefficient();
        employeeService.changeCurrentWorkingHours(employee, workingHours);
    }

}
