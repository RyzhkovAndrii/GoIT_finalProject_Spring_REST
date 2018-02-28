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
public class PaymentResponse extends BaseEntityResponse {

    @ApiModelProperty(position = 1)
    private Date date;

    @ApiModelProperty(position = 2)
    private double workingHours;

    @ApiModelProperty(position = 3)
    private double salary;

    @ApiModelProperty(position = 4)
    private UUID employeeId;

}
