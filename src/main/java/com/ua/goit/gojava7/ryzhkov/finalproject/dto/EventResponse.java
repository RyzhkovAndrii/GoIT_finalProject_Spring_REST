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
public class EventResponse extends BaseEntityResponse {

    @ApiModelProperty(position = 1)
    private String description;

    @ApiModelProperty(position = 2)
    private Date date;

    @ApiModelProperty(position = 3)
    private double duration;

    @ApiModelProperty(position = 4)
    private UUID typeId;

}
