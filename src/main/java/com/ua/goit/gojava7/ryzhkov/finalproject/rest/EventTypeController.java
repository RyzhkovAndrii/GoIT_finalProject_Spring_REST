package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventTypeRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EventTypeResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventTypeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/event-types", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
@RequiredArgsConstructor
public class EventTypeController {

    private final EventTypeService eventTypeService;
    
    private final ModelConversionService conversionService;

    @ApiOperation("view list of event types")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<EventTypeResponse> getList() {
        return conversionService.convert(eventTypeService.findAll(), EventTypeResponse.class);
    }

    @ApiOperation("search event type with name") // todo same ulr like list
    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public EventTypeResponse getByName(@RequestParam String name) {
        return conversionService.convert(eventTypeService.findByName(name), EventTypeResponse.class);
    }

    @ApiOperation("search event type with ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventTypeResponse get(@PathVariable UUID id) {
        return conversionService.convert(eventTypeService.findById(id), EventTypeResponse.class);
    }

    @ApiOperation("add event type")
    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventTypeResponse save(@RequestBody EventTypeRequest eventTypeRequest) {
        EventType eventType = conversionService.convert(eventTypeRequest, EventType.class);
        return conversionService.convert(eventTypeService.save(eventType), EventTypeResponse.class);
    }

    @ApiOperation("update event type")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EventTypeRequest eventTypeRequest) {
        EventType eventType = conversionService.convert(eventTypeRequest, EventType.class);
        eventType.setId(id);
        eventTypeService.update(eventType);
    }

    @ApiOperation("delete eventType")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        eventTypeService.delete(id);
    }

}
