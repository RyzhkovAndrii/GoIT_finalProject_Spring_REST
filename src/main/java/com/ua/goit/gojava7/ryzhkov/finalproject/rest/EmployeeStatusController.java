package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeStatusRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeStatusResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeStatusService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/employee-statuses")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
@RequiredArgsConstructor
public class EmployeeStatusController {

    private final EmployeeStatusService employeeStatusService;

    private final ModelConversionService conversionService;

    @ApiOperation(value = "view list of employee statuses")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<EmployeeStatusResponse> getList() {
        return conversionService.convert(employeeStatusService.findAll(), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "search employee status with name") // todo same ulr like list
    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmployeeStatusResponse getByName(@RequestParam String name) {
        return conversionService.convert(employeeStatusService.findByName(name), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "search employee status with ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public EmployeeStatusResponse get(@PathVariable UUID id) {
        return conversionService.convert(employeeStatusService.findById(id), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "add employee status")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeStatusResponse save(@RequestBody EmployeeStatusRequest employeeStatusRequest) {
        EmployeeStatus employeeStatus = conversionService.convert(employeeStatusRequest, EmployeeStatus.class);
        return conversionService.convert(employeeStatusService.save(employeeStatus), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "update employee status")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EmployeeStatusRequest employeeStatusRequest) {
        EmployeeStatus employeeStatus = conversionService.convert(employeeStatusRequest, EmployeeStatus.class);
        employeeStatus.setId(id);
        employeeStatusService.update(employeeStatus);
    }

    @ApiOperation(value = "delete employee status")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        employeeStatusService.delete(id);
    }

}
