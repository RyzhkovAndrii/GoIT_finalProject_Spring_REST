package com.ua.goit.gojava7.ryzhkov.finalproject.service.impl;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.NamedEntity;
import com.ua.goit.gojava7.ryzhkov.finalproject.repository.NamedEntityRepository;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.NamedEntityService;

import java.io.Serializable;

public abstract class NamedEntityServiceImpl<T extends NamedEntity, ID extends Serializable>
        extends BaseEntityServiceImpl<T, ID> implements NamedEntityService<T, ID> {

    private NamedEntityRepository<T, ID> repository;

    public NamedEntityServiceImpl(NamedEntityRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public T findByName(String name) {
        return repository.findByName(name);
    }

}
