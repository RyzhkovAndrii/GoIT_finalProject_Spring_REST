package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.DepartmentRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentRequestToDepartmentConverter implements Converter<DepartmentRequest, Department> {

    @Override
    public Department convert(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());
        return department;
    }

}
