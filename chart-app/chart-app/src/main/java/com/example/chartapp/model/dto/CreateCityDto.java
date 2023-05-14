package com.example.chartapp.model.dto;

import lombok.Getter;

@Getter
public class CreateCityDto {
    private String name;
    private Long peopleCount;
    private Long regionId;
}
