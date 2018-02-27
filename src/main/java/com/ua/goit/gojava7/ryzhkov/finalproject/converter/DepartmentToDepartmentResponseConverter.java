package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.DepartmentResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentToDepartmentResponseConverter implements Converter<Department, DepartmentResponse> {

    @Override
    public DepartmentResponse convert(Department department) {
        DepartmentResponse departmentResponse = new DepartmentResponse();
        departmentResponse.setId(department.getId());
        departmentResponse.setName(department.getName());
        return departmentResponse;
    }

}
