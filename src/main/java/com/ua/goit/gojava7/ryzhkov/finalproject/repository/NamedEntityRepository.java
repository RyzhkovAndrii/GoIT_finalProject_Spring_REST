package com.ua.goit.gojava7.ryzhkov.finalproject.repository;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.NamedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface NamedEntityRepository<T extends NamedEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    T findByName(String name);

}
