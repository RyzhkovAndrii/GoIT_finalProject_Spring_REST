package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.EmployeePostResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.EmployeePost;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeePostToEmployeePostResponseConverter
        implements Converter<EmployeePost, EmployeePostResponse> {

    @Override
    public EmployeePostResponse convert(EmployeePost employeePost) {
        EmployeePostResponse employeePostResponse = new EmployeePostResponse();
        employeePostResponse.setId(employeePost.getId());
        employeePostResponse.setName(employeePost.getName());
        return employeePostResponse;
    }

}
