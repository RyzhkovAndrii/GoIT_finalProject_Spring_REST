package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.UserRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.UserResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final ModelConversionService conversionService;

    @ApiOperation("view list of users")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<UserResponse> getList() {
        return conversionService.convert(userService.findAll(), UserResponse.class);
    }

    @ApiOperation("search user with ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse get(@PathVariable UUID id) {
        return conversionService.convert(userService.findById(id), UserResponse.class);
    }

    @ApiOperation("add user")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@RequestBody UserRequest userRequest) {
        User user = conversionService.convert(userRequest, User.class);
        return conversionService.convert(userService.save(user), UserResponse.class);
    }

    @ApiOperation("update user")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        User user = conversionService.convert(userRequest, User.class);
        user.setId(id);
        userService.update(user);
    }

    @ApiOperation("delete user")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }

}
