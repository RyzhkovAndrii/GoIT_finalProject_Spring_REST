package com.ua.goit.gojava7.ryzhkov.finalproject.service;

import java.util.UUID;

public interface SecurityService {

    String findLoggedInUsername();

    UUID findLoggedInEmployeeId();

    void autoLogIn(String username, String password);

}
