package com.ua.goit.gojava7.ryzhkov.finalproject.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@ApiModel
@Getter
@Setter
public class UserSaveDto {

    @ApiModelProperty(required = true, position = 1)
    private String username;

    @ApiModelProperty(required = true, position = 2)
    private String password;

    @ApiModelProperty(position = 3)
    private UUID employeeId;

}
