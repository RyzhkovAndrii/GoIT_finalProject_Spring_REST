package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "events/{event}/employees")
public class EventEmployeesController {

    private final EventService eventService;

    private final EmployeeService employeeService;

    @Autowired
    public EventEmployeesController(EventService eventService, EmployeeService employeeService) {
        this.eventService = eventService;
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Employee>> getEventEmployees(@PathVariable("event") UUID id) {
        Event event = eventService.findById(id);
        if (event == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Employee> eventEmployees = event.getEmployees();
        return eventEmployees.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(eventEmployees, HttpStatus.OK); // todo optimized
    }

    @RequestMapping(value = "/{employee}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Employee> addEventEmployee(@PathVariable("event") UUID eventId,
                                                     @PathVariable("employee") UUID employeeId) {
        Event event = eventService.findById(eventId);
        Employee employee = employeeService.findById(employeeId);
        if (event == null || employee == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Employee> eventEmployees = event.getEmployees();
        if (eventEmployees.contains(employee)) {
            return new ResponseEntity<>(HttpStatus.OK); // todo another message
        }
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(eventEmployees);
        newEventEmployees.add(employee); // todo optimized
        event.setEmployees(newEventEmployees);
        eventService.save(event);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{employee}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteEventEmployee(@PathVariable("event") UUID eventId,
                                                    @PathVariable("employee") UUID employeeId) {
        Event event = eventService.findById(eventId);
        Employee employee = employeeService.findById(employeeId);
        if (event == null || employee == null || !event.getEmployees().contains(employee)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        Collection<Employee> eventEmployees = event.getEmployees();
        Set<Employee> newEventEmployees = new HashSet<>();
        newEventEmployees.addAll(eventEmployees);
        newEventEmployees.remove(employee); // todo optimized
        event.setEmployees(newEventEmployees);
        eventService.save(event);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
