package com.ua.goit.gojava7.ryzhkov.finalproject.rest;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.NamedEntity;
import com.ua.goit.gojava7.ryzhkov.finalproject.service.NamedEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public abstract class NamedEntityController<T extends NamedEntity, ID extends Serializable>
        extends BaseEntityController<T, ID> {

    private NamedEntityService<T, ID> namedEntityService;

    public NamedEntityController(NamedEntityService<T, ID> namedEntityService) {
        super(namedEntityService);
        this.namedEntityService = namedEntityService;
    }

    @RequestMapping(params = "name", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<T> getByName(@RequestParam("name") String name) {
        T entity = namedEntityService.findByName(name);
        return entity != null
                ? new ResponseEntity<>(entity, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
