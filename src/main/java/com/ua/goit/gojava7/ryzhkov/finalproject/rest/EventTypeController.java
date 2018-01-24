package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("event-types")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EventTypeController {

    private EventTypeService eventTypeService;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService) {
        this.eventTypeService = eventTypeService;
    }

    @ApiOperation(value = "view list of event types", response = Collection.class)
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<EventType>> getList() {
        return new ResponseEntity<>(eventTypeService.findAll(), HttpStatus.OK);
    }

    @ApiOperation(value = "search event type with name", response = EventType.class) // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> getByName(@RequestParam String name) {
        return new ResponseEntity<>(eventTypeService.findByName(name), HttpStatus.OK);
    }

    @ApiOperation(value = "search event type with ID", response = EventType.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> get(@PathVariable UUID id) {
        return new ResponseEntity<>(eventTypeService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "add event type", response = EventType.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> save(@RequestBody EventType eventType) {
        return new ResponseEntity<>(eventTypeService.save(eventType), HttpStatus.CREATED);
    }

    @ApiOperation(value = "update event type")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody EventType eventType) {
        eventType.setId(id);
        eventTypeService.save(eventType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete eventType")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        eventTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
