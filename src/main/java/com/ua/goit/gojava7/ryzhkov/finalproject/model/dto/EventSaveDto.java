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
public class EventSaveDto {

    @ApiModelProperty(position = 1)
    private String description;

    @ApiModelProperty(required = true, position = 2)
    private Date date;

    @ApiModelProperty(required = true, position = 3)
    private double duration;

    @ApiModelProperty(required = true, position = 4)
    private UUID typeId;

}
