package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.BaseEntity;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.BaseEntityService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.Collection;

public abstract class BaseEntityServiceImpl<T extends BaseEntity, ID extends Serializable>
        implements BaseEntityService<T, ID> {

    private JpaRepository<T, ID> repository;

    public BaseEntityServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    public T findById(ID id) {
        return repository.findOne(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public void delete(T t) {
        repository.delete(t);
    }

    @Override
    public void delete(ID id) {
        repository.delete(id);
    }

}
