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
        Collection<EventType> list = eventTypeService.findAll();
        return !list.isEmpty()
                ? new ResponseEntity<>(list, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search event type with name", response = EventType.class) // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> getByName(@RequestParam String name) {
        EventType eventType = eventTypeService.findByName(name);
        return eventType != null
                ? new ResponseEntity<>(eventType, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "search event type with ID", response = EventType.class)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> get(@PathVariable UUID id) {
        EventType eventType = eventTypeService.findById(id);
        return eventType != null
                ? new ResponseEntity<>(eventType, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "add event type", response = EventType.class)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<EventType> save(@RequestBody EventType eventType) {
        eventTypeService.save(eventType);
        return new ResponseEntity<>(eventType, HttpStatus.CREATED);
    }

    @ApiOperation(value = "update event type")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody EventType eventType) {
        EventType entity = eventTypeService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        eventType.setId(entity.getId());
        eventTypeService.save(eventType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "delete eventType")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        if (eventTypeService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        eventTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
