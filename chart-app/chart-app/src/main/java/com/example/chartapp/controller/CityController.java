package com.example.chartapp.controller;

import com.example.chartapp.model.dto.CreateCityDto;
import com.example.chartapp.model.entity.City;
import com.example.chartapp.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @PostMapping
    public City saveCity(@RequestBody CreateCityDto dto) {
        return cityService.saveCity(dto);
    }

    @GetMapping("/{id}")
    public City findCityById(@PathVariable("id") Long id){
        return cityService.findCityById(id);
    }

    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
    @PutMapping("/{id}")
    public City updateCity(@PathVariable("id") Long id, @RequestBody City updatedCity) {
        return cityService.updateCity(id,updatedCity);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }
}
