package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Employee;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.SecurityService;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SecurityServiceImpl implements SecurityService {

    private UserDetailsService userDetailsService;

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Autowired
    public SecurityServiceImpl(UserDetailsService userDetailsService,
                               AuthenticationManager authenticationManager,
                               UserService userService) {
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        return userDetails instanceof UserDetails
                ? ((UserDetails) userDetails).getUsername()
                : null;
    }

    @Override
    public UUID findLoggedInEmployeeId() { // todo not working
        String userName = findLoggedInUsername();
        userName = userDetailsService.loadUserByUsername(userName).getUsername();
        User user = userService.findByUserName(userName);
        Employee employee = user.getEmployee();
        return employee != null
                ? employee.getId()
                : null;
    }

    @Override
    public void autoLogIn(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails.getPassword(), userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
    }

}

