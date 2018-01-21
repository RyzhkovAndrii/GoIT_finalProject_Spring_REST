package com.ua.goit.gojava7.ryzhkov.finalproject.service;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;

import java.util.UUID;

public interface UserService extends BaseEntityService<User, UUID> {

    User findByUserName(String username);

}
