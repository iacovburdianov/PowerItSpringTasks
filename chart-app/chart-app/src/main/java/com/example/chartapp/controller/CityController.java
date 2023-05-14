package com.example.chartapp.controller;

import com.example.chartapp.dto.CreateCityDto;
import com.example.chartapp.entity.City;
import com.example.chartapp.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/sorted/{field}")
    public List<City> findCitiesWithSorting(@RequestParam String field) {
        return cityService.findCitiesWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<City> findCitiesWithPagination(@RequestParam int offset, @RequestParam int pageSize) {
        return cityService.findCitiesWithPagination(offset,pageSize);
    }

    @GetMapping("/sorted-paginated/{offset}/{pageSize}/{field}")
    public Page<City> findCitiesWithPaginationAndSorting(@RequestParam int offset, @RequestParam int pageSize, @RequestParam String field) {
        return cityService.findCitiesWithPaginationAndSorting(offset, pageSize, field);
    }
}
