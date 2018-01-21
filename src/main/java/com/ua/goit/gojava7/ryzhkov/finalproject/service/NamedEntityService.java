package com.ua.goit.gojava7.ryzhkov.finalproject.service;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.NamedEntity;

public interface NamedEntityService<T extends NamedEntity, ID> extends BaseEntityService<T, ID> {

    T findByName(String name);

}
