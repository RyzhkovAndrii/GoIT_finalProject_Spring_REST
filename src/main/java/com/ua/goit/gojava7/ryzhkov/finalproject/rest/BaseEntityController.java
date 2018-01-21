package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.BaseEntity;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.BaseEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.util.Collection;

public abstract class BaseEntityController<T extends BaseEntity, ID extends Serializable> {

    private BaseEntityService<T, ID> baseEntityService;

    public BaseEntityController(BaseEntityService<T, ID> baseEntityService) {
        this.baseEntityService = baseEntityService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<T>> getList() {
        Collection<T> entityCollection = baseEntityService.findAll();
        return !entityCollection.isEmpty()
                ? new ResponseEntity<>(entityCollection, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<T> get(@PathVariable("id") ID id) {
        T entity = baseEntityService.findById(id);
        return entity != null
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<T> save(@RequestBody T t) {
        return new ResponseEntity<>(baseEntityService.save(t), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<T> update(@PathVariable("id") ID id, @RequestBody T entity) {
        T oldEntity = baseEntityService.findById(id);
        if (oldEntity == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        entity.setId(oldEntity.getId());
        return new ResponseEntity<>(baseEntityService.save(entity), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> delete(@PathVariable("id") ID id) {
        baseEntityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
