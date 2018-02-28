package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.DepartmentRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.DepartmentResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    private final ModelConversionService conversionService;

    @ApiOperation(value = "view list of departments")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<DepartmentResponse> getList() {
        return conversionService.convert(departmentService.findAll(), DepartmentResponse.class);
    }

    @ApiOperation(value = "search department with name") // todo same ulr like list
    @GetMapping(params = "name", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse getByName(@RequestParam String name) {
        return conversionService.convert(departmentService.findByName(name), DepartmentResponse.class);
    }

    @ApiOperation(value = "search department with ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public DepartmentResponse get(@PathVariable UUID id) {
        return conversionService.convert(departmentService.findById(id), DepartmentResponse.class);
    }

    @ApiOperation(value = "add department")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public DepartmentResponse save(@RequestBody DepartmentRequest departmentRequest) {
        Department department = conversionService.convert(departmentRequest, Department.class);
        return conversionService.convert(departmentService.save(department), DepartmentResponse.class);
    }

    @ApiOperation(value = "update department")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody DepartmentRequest departmentRequest) {
        Department department = conversionService.convert(departmentRequest, Department.class);
        department.setId(id);
        departmentService.save(department);
    }

    @ApiOperation(value = "delete department")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        departmentService.delete(id);
    }

}
