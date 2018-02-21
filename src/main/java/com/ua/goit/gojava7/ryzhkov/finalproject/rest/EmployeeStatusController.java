package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EmployeeStatusDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EmployeeStatusSaveDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeStatusService;
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
@RequestMapping("/employee-statuses")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EmployeeStatusController {

    private EmployeeStatusService employeeStatusService;

    private ModelMapper modelMapper;

    @Autowired
    public EmployeeStatusController(EmployeeStatusService employeeStatusService, ModelMapper modelMapper) {
        this.employeeStatusService = employeeStatusService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of employee statuses")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<EmployeeStatusDto> getList() {
        return convertToDto(employeeStatusService.findAll());
    }

    @ApiOperation(value = "search employee status with name") // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeStatusDto getByName(@RequestParam String name) {
        return convertToDto(employeeStatusService.findByName(name));
    }

    @ApiOperation(value = "search employee status with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeStatusDto get(@PathVariable UUID id) {
        return convertToDto(employeeStatusService.findById(id));
    }

    @ApiOperation(value = "add employee status")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EmployeeStatusDto save(@RequestBody EmployeeStatusSaveDto employeeStatusDto) {
        return convertToDto(employeeStatusService.save(convertToEntity(employeeStatusDto)));
    }

    @ApiOperation(value = "update employee status")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EmployeeStatusSaveDto employeeStatusDto) {
        EmployeeStatus employeeStatus = convertToEntity(employeeStatusDto);
        employeeStatus.setId(id);
        employeeStatusService.update(employeeStatus);
    }

    @ApiOperation(value = "delete employee status")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        employeeStatusService.delete(id);
    }

    private EmployeeStatus convertToEntity(EmployeeStatusSaveDto dto) {
        return modelMapper.map(dto, EmployeeStatus.class);
    }

    private EmployeeStatusDto convertToDto(EmployeeStatus employeeStatus) {
        return modelMapper.map(employeeStatus, EmployeeStatusDto.class);
    }

    private Collection<EmployeeStatusDto> convertToDto(Collection<EmployeeStatus> employeeStatuses) {
        return employeeStatuses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
