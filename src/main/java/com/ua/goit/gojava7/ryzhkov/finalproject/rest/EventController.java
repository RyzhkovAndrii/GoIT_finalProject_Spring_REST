package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Event;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EventDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EventSaveDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EventService;
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
@RequestMapping("/events")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EventController {

    private EventService eventService;

    private ModelMapper modelMapper;

    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of events")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<EventDto> getList() {
        return convertToDto(eventService.findAll());
    }

    @ApiOperation(value = "search event with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EventDto get(@PathVariable UUID id) {
        return convertToDto(eventService.findById(id));
    }

    @ApiOperation(value = "add event")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EventDto save(@RequestBody EventSaveDto eventDto) {
        return convertToDto(eventService.save(convertToEntity(eventDto)));
    }

    @ApiOperation(value = "update event")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EventSaveDto eventDto) {
        Event event = convertToEntity(eventDto);
        event.setId(id);
        eventService.update(event);
    }

    @ApiOperation(value = "delete event")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        eventService.delete(id);
    }

    private Event convertToEntity(EventSaveDto dto) {
        return modelMapper.map(dto, Event.class); // todo Converter
    }

    private EventDto convertToDto(Event event) {
        return modelMapper.map(event, EventDto.class);
    }

    private Collection<EventDto> convertToDto(Collection<Event> events) {
        return events.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
