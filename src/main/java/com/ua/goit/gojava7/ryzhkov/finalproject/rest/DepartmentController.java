package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.DepartmentDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.DepartmentSaveDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.DepartmentService;
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
@RequestMapping("/departments")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class DepartmentController {

    private DepartmentService departmentService;

    private ModelMapper modelMapper;

    @Autowired
    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of departments")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<DepartmentDto> getList() {
        return convertToDto(departmentService.findAll());
    }

    @ApiOperation(value = "search department with name") // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentDto getByName(@RequestParam String name) {
        return convertToDto(departmentService.findByName(name));
    }

    @ApiOperation(value = "search department with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public DepartmentDto get(@PathVariable UUID id) {
        return convertToDto(departmentService.findById(id));
    }

    @ApiOperation(value = "add department")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DepartmentDto save(@RequestBody DepartmentSaveDto departmentDto) {
        return convertToDto(departmentService.save(convertToEntity(departmentDto)));
    }

    @ApiOperation(value = "update department")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody DepartmentSaveDto departmentDto) {
        Department department = convertToEntity(departmentDto);
        department.setId(id);
        departmentService.save(department);
    }

    @ApiOperation(value = "delete department")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        departmentService.delete(id);
    }

    private Department convertToEntity(DepartmentSaveDto dto) {
        return modelMapper.map(dto, Department.class);
    }

    private DepartmentDto convertToDto(Department department) {
        return modelMapper.map(department, DepartmentDto.class);
    }

    private Collection<DepartmentDto> convertToDto(Collection<Department> departments) {
        return departments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
