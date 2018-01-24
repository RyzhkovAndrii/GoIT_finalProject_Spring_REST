package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.Role;
import com.ua.goit.gojava7.ryzhkov.finalproject.model.User;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.UserRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findOne(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setRegistrationDate(new Date());
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(id);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(User user, Role role) {
        Set<Role> newUserRoles = new HashSet<>();
        newUserRoles.addAll(user.getRoles());
        newUserRoles.add(role); // todo optimized
        user.setRoles(newUserRoles);
        userRepository.save(user);
    }

    @Override
    public void deleteRoleFromUser(User user, Role role) {
        Collection<Role> userRoles = user.getRoles();
        Set<Role> newUserRoles = new HashSet<>();
        newUserRoles.addAll(userRoles);
        newUserRoles.remove(role); // todo optimized
        user.setRoles(newUserRoles);
        userRepository.save(user);
    }


}
