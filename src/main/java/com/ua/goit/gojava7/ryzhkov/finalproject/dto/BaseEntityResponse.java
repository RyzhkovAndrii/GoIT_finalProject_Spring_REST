package com.ua.goit.gojava7.ryzhkov.finalproject.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class BaseEntityResponse {

    @ApiModelProperty(position = -2)
    private UUID id;

}
