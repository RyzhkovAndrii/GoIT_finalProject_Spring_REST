package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EventTypeDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EventTypeSaveDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventTypeService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("event-types")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EventTypeController {

    private EventTypeService eventTypeService;
    
    private ModelMapper modelMapper;

    @Autowired
    public EventTypeController(EventTypeService eventTypeService, ModelMapper modelMapper) {
        this.eventTypeService = eventTypeService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of event types")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<EventTypeDto> getList() {
        return convertToDto(eventTypeService.findAll());
    }

    @ApiOperation(value = "search event type with name") // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EventTypeDto getByName(@RequestParam String name) {
        return convertToDto(eventTypeService.findByName(name));
    }

    @ApiOperation(value = "search event type with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EventTypeDto get(@PathVariable UUID id) {
        return convertToDto(eventTypeService.findById(id));
    }

    @ApiOperation(value = "add event type")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EventTypeDto save(@RequestBody EventTypeSaveDto eventTypeDto) {
        return convertToDto(eventTypeService.save(convertToEntity(eventTypeDto)));
    }

    @ApiOperation(value = "update event type")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EventTypeSaveDto eventTypeDto) {
        EventType eventType = convertToEntity(eventTypeDto);
        eventType.setId(id);
        eventTypeService.update(eventType);
    }

    @ApiOperation(value = "delete eventType")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        eventTypeService.delete(id);
    }

    private EventType convertToEntity(EventTypeSaveDto dto) {
        return modelMapper.map(dto, EventType.class);
    }

    private EventTypeDto convertToDto(EventType eventType) {
        return modelMapper.map(eventType, EventTypeDto.class);
    }

    private Collection<EventTypeDto> convertToDto(Collection<EventType> eventTypes) {
        return eventTypes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
