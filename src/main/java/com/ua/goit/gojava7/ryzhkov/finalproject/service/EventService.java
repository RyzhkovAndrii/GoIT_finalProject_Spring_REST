package com.ua.goit.gojava7.ryzhkov.finalproject.service;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;

import java.util.UUID;

public interface EventService extends BaseEntityService<Event, UUID> {

    void update(Event event);

    void addEmployeeToEvent(UUID eventId, UUID employeeId);

    void deleteEmployeeFromEvent(UUID eventId, UUID employeeId);

}
