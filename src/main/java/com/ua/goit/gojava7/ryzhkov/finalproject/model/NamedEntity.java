package com.ua.goit.gojava7.ryzhkov.finalproject.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    @ApiModelProperty(position = -1)
    private String name;

}
