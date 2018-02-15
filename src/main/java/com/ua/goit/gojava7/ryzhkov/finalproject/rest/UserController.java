package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.UserDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.dto.UserSaveDto;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeeService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
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
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private UserService userService;

    private EmployeeService employeeService;

    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, EmployeeService employeeService, ModelMapper modelMapper) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @ApiOperation(value = "view list of users")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Collection<UserDto> getList() {
        return convertToDto(userService.findAll());
    }

    @ApiOperation(value = "search user with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto get(@PathVariable UUID id) {
        return convertToDto(userService.findById(id));
    }

    @ApiOperation(value = "add user")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto save(@RequestBody UserSaveDto userDto) {
        User user = convertToEntity(userDto);
        userService.save(user);
        return convertToDto(user);
    }

    @ApiOperation(value = "update user")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody UserSaveDto userDto) {
        User user = convertToEntity(userDto);
        user.setId(id);
        userService.update(user);
    }

    @ApiOperation(value = "delete user")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

    private User convertToEntity(UserSaveDto dto) {
        User user = modelMapper.map(dto, User.class);
        if (dto.getEmployeeId() != null) {
            user.setEmployee(employeeService.findById(dto.getEmployeeId()));
        }
        return user;
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private Collection<UserDto> convertToDto(Collection<User> users) {
        return users.stream().map(this::convertToDto).collect(Collectors.toList());
    }

}
