package com.ua.goit.gojava7.ryzhkov.finalproject.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@ApiModel
@Getter
@Setter
public class EmployeeSaveDto {

    @ApiModelProperty(required = true, position = 1)
    private String email;

    @ApiModelProperty(required = true, position = 2)
    private String firstName;

    @ApiModelProperty(required = true, position = 3)
    private String lastName;

    @ApiModelProperty(required = true, position = 4)
    private UUID departmentId;

    @ApiModelProperty(required = true, position = 5)
    private UUID postId;

    @ApiModelProperty(required = true, position = 6)
    private double hourlyRate;

    @ApiModelProperty(required = true, position = 7)
    private UUID statusId;

    @ApiModelProperty(position = 8)
    private UUID userId;

}
