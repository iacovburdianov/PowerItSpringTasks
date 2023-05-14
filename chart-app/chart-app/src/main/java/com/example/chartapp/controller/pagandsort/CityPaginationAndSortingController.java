package com.example.chartapp.controller.pagandsort;

import com.example.chartapp.model.dto.APIResponse;
import com.example.chartapp.model.entity.City;
import com.example.chartapp.service.pagandsort.CityPaginationAndSortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cityPagAndSort")
@RequiredArgsConstructor
public class CityPaginationAndSortingController {
    private final CityPaginationAndSortingService service;

    @GetMapping
    public APIResponse<List<City>> getCities() {
        List<City> allCities = service.findAllCities();
        return new APIResponse<>(allCities.size(), allCities);
    }

    @GetMapping("/{field}")
    public APIResponse<List<City>> getCitiesWithSort(@PathVariable String field) {
        List<City> allCities = service.findCitiesWithSorting(field);
        return new APIResponse<>(allCities.size(), allCities);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public APIResponse<Page<City>> getCitiesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<City> cities = service.findCitiesWithPagination(offset,pageSize);
        return new APIResponse<>(cities.getSize(), cities);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public APIResponse<Page<City>> getCountriesWithPaginationAndSort(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field) {
        Page<City> citiesiesWithPaginationAndSorting = service.findCitiesWithPaginationAndSorting(offset,pageSize,field);
        return new APIResponse<>(citiesiesWithPaginationAndSorting.getSize(), citiesiesWithPaginationAndSorting);
    }
}
