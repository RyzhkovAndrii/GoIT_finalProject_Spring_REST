package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/events")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    private final ModelConversionService conversionService;

    @ApiOperation(value = "view list of events")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<EventResponse> getList() {
        return conversionService.convert(eventService.findAll(), EventResponse.class);
    }

    @ApiOperation(value = "search event with ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EventResponse get(@PathVariable UUID id) {
        return conversionService.convert(eventService.findById(id), EventResponse.class);
    }

    @ApiOperation(value = "add event")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EventResponse save(@RequestBody EventRequest eventRequest) {
        Event event = conversionService.convert(eventRequest, Event.class);
        return conversionService.convert(eventService.save(event), EventResponse.class);
    }

    @ApiOperation(value = "update event")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EventRequest eventRequest) {
        Event event = conversionService.convert(eventRequest, Event.class);
        event.setId(id);
        eventService.update(event);
    }

    @ApiOperation(value = "delete event")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        eventService.delete(id);
    }

}
