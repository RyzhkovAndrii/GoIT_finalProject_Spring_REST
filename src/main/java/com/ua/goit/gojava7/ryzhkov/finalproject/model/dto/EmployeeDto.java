package com.ua.goit.gojava7.ryzhkov.finalproject.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@ApiModel
@Getter
@Setter
public class EmployeeDto extends BaseEntityDto {

    @ApiModelProperty(position = 1)
    private String email;

    @ApiModelProperty(position = 2)
    private String firstName;

    @ApiModelProperty(position = 3)
    private String lastName;

    @ApiModelProperty(position = 4)
    private UUID departmentId;

    @ApiModelProperty(position = 5)
    private UUID postId;

    @ApiModelProperty(position = 6)
    private double hourlyRate;

    @ApiModelProperty(position = 7)
    private UUID statusId;

    @ApiModelProperty(position = 8)
    private UUID userId;

    @ApiModelProperty(position = 9)
    private double currentWorkingHours;

}
