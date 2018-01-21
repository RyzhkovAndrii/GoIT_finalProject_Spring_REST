package com.ua.goit.gojava7.ryzhkov.finalproject.repository;

import com.ua.goit.gojava7.ryzhkov.finalproject.model.EventType;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventTypeRepository extends NamedEntityRepository<EventType, UUID> {
}
