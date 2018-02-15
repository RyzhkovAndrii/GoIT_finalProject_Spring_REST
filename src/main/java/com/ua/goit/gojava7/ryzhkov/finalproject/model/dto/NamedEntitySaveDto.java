package com.ua.goit.gojava7.ryzhkov.finalproject.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class NamedEntitySaveDto {

    @ApiModelProperty(required = true, position = -1)
    private String name;

}
