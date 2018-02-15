package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "uuid-gen")
//    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Type(type = "org.hibernate.type.UUIDBinaryType")
    @Column(name = "id")
    private UUID id;

}
