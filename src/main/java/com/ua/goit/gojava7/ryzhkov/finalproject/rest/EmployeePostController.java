package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.converter.ModelConversionService;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeePostRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeePostResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.EmployeePostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/employee-posts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MODERATOR')")
@RequiredArgsConstructor
public class EmployeePostController {

    private final EmployeePostService employeePostService;

    private final ModelConversionService conversionService;

    @ApiOperation(value = "view list of employee posts")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Collection<EmployeePostResponse> getList() {
        return conversionService.convert(employeePostService.findAll(), EmployeePostResponse.class);
    }

    @ApiOperation(value = "search employee post with name") // todo same ulr like list
    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public EmployeePostResponse getByName(@RequestParam String name) {
        return conversionService.convert(employeePostService.findByName(name), EmployeePostResponse.class);
    }

    @ApiOperation(value = "search employee post with ID")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EmployeePostResponse get(@PathVariable UUID id) {
        return conversionService.convert(employeePostService.findById(id), EmployeePostResponse.class);
    }

    @ApiOperation(value = "add employee post")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeePostResponse save(@RequestBody EmployeePostRequest employeePostRequest) {
        EmployeePost employeePost = conversionService.convert(employeePostRequest, EmployeePost.class);
        return conversionService.convert(employeePostService.save(employeePost), EmployeePostResponse.class);
    }

    @ApiOperation(value = "update employee post")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable UUID id, @RequestBody EmployeePostRequest employeePostRequest) {
        EmployeePost employeePost = conversionService.convert(employeePostRequest, EmployeePost.class);
        employeePost.setId(id);
        employeePostService.update(employeePost);
    }

    @ApiOperation(value = "delete employee post")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable UUID id) {
        employeePostService.delete(id);
    }

}
