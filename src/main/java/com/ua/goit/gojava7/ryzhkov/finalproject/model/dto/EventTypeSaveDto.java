package com.ua.goit.gojava7.ryzhkov.finalproject.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class EventTypeSaveDto extends NamedEntitySaveDto {

    @ApiModelProperty(required = true, position = 1)
    private double hourlyRateCoefficient;

}
