package com.ua.goit.gojava7.ryzhkov.finalproject.converter;

import com.ua.goit.gojava7.ryzhkov.finalproject.dto.UserResponse;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseConverter implements Converter<User, UserResponse> {

    @Override
    public UserResponse convert(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setPassword(user.getPassword());
        userResponse.setRegistrationDate(user.getRegistrationDate());
        if (user.getEmployee() != null) {
            userResponse.setEmployeeId(user.getEmployee().getId());
        }
        return userResponse;
    }

}
