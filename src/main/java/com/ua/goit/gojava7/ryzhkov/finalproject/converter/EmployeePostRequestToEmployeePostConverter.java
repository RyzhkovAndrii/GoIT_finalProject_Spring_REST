package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeePostRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeePostRequestToEmployeePostConverter
        implements Converter<EmployeePostRequest, EmployeePost> {

    @Override
    public EmployeePost convert(EmployeePostRequest employeePostRequest) {
        EmployeePost employeePost = new EmployeePost();
        employeePost.setName(employeePostRequest.getName());
        return employeePost;
    }

}
