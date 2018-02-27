package com.ua.goit.gojava7.ryzhkov.finalproject.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@ApiModel
@Getter
@Setter
public class UserResponse extends BaseEntityResponse {

    @ApiModelProperty(position = 1)
    private String username;

    @ApiModelProperty(position = 2)
    private String password;

    @ApiModelProperty(position = 3)
    private Date registrationDate;

    @ApiModelProperty(position = 4)
    private UUID employeeId;

}
