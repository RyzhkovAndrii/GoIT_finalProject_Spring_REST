package com.ua.goit.gojava7.ryzhkov.finalproject.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@ApiModel
@Getter
@Setter
public class UserDto {

    @ApiModelProperty(position = 1)
    private UUID id;

    @ApiModelProperty(position = 2)
    private String username;

    @ApiModelProperty(position = 3)
    private String password;

    @ApiModelProperty(position = 4)
    private Date registrationDate;

    @ApiModelProperty(position = 5)
    private UUID employeeId;

}
