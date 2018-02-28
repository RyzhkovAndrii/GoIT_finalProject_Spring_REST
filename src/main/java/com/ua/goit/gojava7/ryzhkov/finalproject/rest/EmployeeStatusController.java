package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeeStatus;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeStatusResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeeStatusRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeStatusService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/employee-statuses")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EmployeeStatusController {

    private EmployeeStatusService employeeStatusService;

    private ModelConversionService conversionService;

    @Autowired
    public EmployeeStatusController(EmployeeStatusService employeeStatusService, ModelConversionService conversionService) {
        this.employeeStatusService = employeeStatusService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "view list of employee statuses")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<EmployeeStatusResponse> getList() {
        return conversionService.convert(employeeStatusService.findAll(), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "search employee status with name") // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeStatusResponse getByName(@RequestParam String name) {
        return conversionService.convert(employeeStatusService.findByName(name), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "search employee status with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeeStatusResponse get(@PathVariable UUID id) {
        return conversionService.convert(employeeStatusService.findById(id), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "add employee status")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EmployeeStatusResponse save(@RequestBody EmployeeStatusRequest employeeStatusRequest) {
        EmployeeStatus employeeStatus = conversionService.convert(employeeStatusRequest, EmployeeStatus.class);
        return conversionService.convert(employeeStatusService.save(employeeStatus), EmployeeStatusResponse.class);
    }

    @ApiOperation(value = "update employee status")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EmployeeStatusRequest employeeStatusRequest) {
        EmployeeStatus employeeStatus = conversionService.convert(employeeStatusRequest, EmployeeStatus.class);
        employeeStatus.setId(id);
        employeeStatusService.update(employeeStatus);
    }

    @ApiOperation(value = "delete employee status")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        employeeStatusService.delete(id);
    }

}
