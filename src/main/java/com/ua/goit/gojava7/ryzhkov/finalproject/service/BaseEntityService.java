package com.ua.goit.gojava7.ryzhkov.finalproject.service;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.BaseEntity;

import java.util.Collection;

public interface BaseEntityService<T extends BaseEntity, ID> {

    T findById(ID id);

    Collection<T> findAll();

    T save(T t);

    void delete(T t);

    void delete(ID id);

}
