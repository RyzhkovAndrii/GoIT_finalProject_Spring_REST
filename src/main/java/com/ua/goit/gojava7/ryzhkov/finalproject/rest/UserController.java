package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.UserRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.UserResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {

    private UserService userService;

    private ModelConversionService conversionService;

    @Autowired
    public UserController(UserService userService, ModelConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }

    @ApiOperation(value = "view list of users")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserResponse> getList() {
        return conversionService.convert(userService.findAll(), UserResponse.class);
    }

    @ApiOperation(value = "search user with ID")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserResponse get(@PathVariable UUID id) {
        return conversionService.convert(userService.findById(id), UserResponse.class);
    }

    @ApiOperation(value = "add user")
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@RequestBody UserRequest userRequest) {
        User user = conversionService.convert(userRequest, User.class);
        return conversionService.convert(userService.save(user), UserResponse.class);
    }

    @ApiOperation(value = "update user")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        User user = conversionService.convert(userRequest, User.class);
        user.setId(id);
        userService.update(user);
    }

    @ApiOperation(value = "delete user")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

}
