package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.DepartmentResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.DepartmentRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class DepartmentController {

    private DepartmentService departmentService;

    private ModelConversionService conversionService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, ModelConversionService conversionService) {
        this.departmentService = departmentService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "view list of departments")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<DepartmentResponse> getList() {
        return conversionService.convert(departmentService.findAll(), DepartmentResponse.class);
    }

    @ApiOperation(value = "search department with name") // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentResponse getByName(@RequestParam String name) {
        return conversionService.convert(departmentService.findByName(name), DepartmentResponse.class);
    }

    @ApiOperation(value = "search department with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentResponse get(@PathVariable UUID id) {
        return conversionService.convert(departmentService.findById(id), DepartmentResponse.class);
    }

    @ApiOperation(value = "add department")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DepartmentResponse save(@RequestBody DepartmentRequest departmentRequest) {
        Department department = conversionService.convert(departmentRequest, Department.class);
        return conversionService.convert(departmentService.save(department), DepartmentResponse.class);
    }

    @ApiOperation(value = "update department")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody DepartmentRequest departmentRequest) {
        Department department = conversionService.convert(departmentRequest, Department.class);
        department.setId(id);
        departmentService.save(department);
    }

    @ApiOperation(value = "delete department")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        departmentService.delete(id);
    }

}
