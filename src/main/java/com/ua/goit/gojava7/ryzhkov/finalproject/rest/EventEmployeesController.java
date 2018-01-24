package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("events/{event}/employees")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EventEmployeesController {

    private final EventService eventService;

    @Autowired
    public EventEmployeesController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<Employee>> getEventEmployees(@PathVariable("event") UUID id) {
        return new ResponseEntity<>(eventService.findById(id).getEmployees(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{employee}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> addEventEmployee(@PathVariable("event") UUID eventId,
                                                 @PathVariable("employee") UUID employeeId) {
        eventService.addEmployeeToEvent(eventId, employeeId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{employee}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteEventEmployee(@PathVariable("event") UUID eventId,
                                                    @PathVariable("employee") UUID employeeId) {
        eventService.deleteEmployeeFromEvent(eventId, employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
