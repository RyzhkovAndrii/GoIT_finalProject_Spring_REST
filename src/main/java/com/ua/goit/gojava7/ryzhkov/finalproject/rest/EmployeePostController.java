package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EmployeePostDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.EmployeePostSaveDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeePostService;
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
@RequestMapping("/employee-posts")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
public class EmployeePostController {

    private EmployeePostService employeePostService;

    private ModelMapper modelMapper;

    @Autowired
    public EmployeePostController(EmployeePostService employeePostService, ModelMapper modelMapper) {
        this.employeePostService = employeePostService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of employee posts")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<EmployeePostDto> getList() {
        return convertToDto(employeePostService.findAll());
    }

    @ApiOperation(value = "search employee post with name") // todo same ulr like list
    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeePostDto getByName(@RequestParam String name) {
        return convertToDto(employeePostService.findByName(name));
    }

    @ApiOperation(value = "search employee post with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public EmployeePostDto get(@PathVariable UUID id) {
        return convertToDto(employeePostService.findById(id));
    }

    @ApiOperation(value = "add employee post")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EmployeePostDto save(@RequestBody EmployeePostSaveDto employeePostDto) {
        return convertToDto(employeePostService.save(convertToEntity(employeePostDto)));
    }

    @ApiOperation(value = "update employee post")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EmployeePostSaveDto employeePostDto) {
        EmployeePost employeePost = convertToEntity(employeePostDto);
        employeePost.setId(id);
        employeePostService.update(employeePost);
    }

    @ApiOperation(value = "delete employee post")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        employeePostService.delete(id);
    }

    private EmployeePost convertToEntity(EmployeePostSaveDto dto) {
        return modelMapper.map(dto, EmployeePost.class);
    }

    private EmployeePostDto convertToDto(EmployeePost employeePost) {
        return modelMapper.map(employeePost, EmployeePostDto.class);
    }

    private Collection<EmployeePostDto> convertToDto(Collection<EmployeePost> employeePosts) {
        return employeePosts.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
