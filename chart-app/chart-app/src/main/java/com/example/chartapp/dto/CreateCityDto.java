package com.example.chartapp.dto;


public class CreateCityDto {
    private String name;
    private Long peopleCount;
    private Long regionId;

    public String getName() {
        return name;
    }

    public Long getPeopleCount() {
        return peopleCount;
    }

    public Long getRegionId() {
        return regionId;
    }

    @Override
    public String toString() {
        return "CreateCityDto{" +
                "name='" + name + '\'' +
                ", peopleCount=" + peopleCount +
                ", regionId=" + regionId +
                '}';
    }
}
