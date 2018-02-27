package com.ua.goit.gojava7.ryzhkov.finalproject.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class EventTypeRequest extends NamedEntityRequest {

    @ApiModelProperty(required = true, position = 1)
    private double hourlyRateCoefficient;

}
