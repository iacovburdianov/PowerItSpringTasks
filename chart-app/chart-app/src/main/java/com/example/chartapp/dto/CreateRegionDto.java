package com.example.chartapp.dto;



public class CreateRegionDto {
    private String name;
    private Long countryId;

    public String getName() {
        return name;
    }

    public Long getCountryId() {
        return countryId;
    }

    @Override
    public String toString() {
        return "CreateRegionDto{" +
                "name='" + name + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}
