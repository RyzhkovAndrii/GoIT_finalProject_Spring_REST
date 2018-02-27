package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.UserRequest;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRequestToUserConverter implements Converter<UserRequest, User> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public User convert(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        if (userRequest.getEmployeeId() != null) {
            user.setEmployee(employeeRepository.findOne(userRequest.getEmployeeId())); // todo bad request exception
        }
        return user;
    }
}
