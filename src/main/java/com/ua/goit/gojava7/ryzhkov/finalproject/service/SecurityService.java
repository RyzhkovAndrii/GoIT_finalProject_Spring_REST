package com.ua.goit.gojava7.ryzhkov.finalproject.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogIn(String username, String password);

}
