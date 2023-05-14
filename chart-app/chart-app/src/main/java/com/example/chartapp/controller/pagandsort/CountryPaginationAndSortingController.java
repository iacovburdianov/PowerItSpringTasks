package com.example.chartapp.controller.pagandsort;

import com.example.chartapp.model.dto.APIResponse;
import com.example.chartapp.model.entity.Country;
import com.example.chartapp.service.pagandsort.CountryPaginationAndSortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/countryPagAndSort")
public class CountryPaginationAndSortingController {
    private final CountryPaginationAndSortingService service;

    @GetMapping
    public APIResponse<List<Country>> getCountries() {
        List<Country> allCountries = service.findAllCountries();
        return new APIResponse<>(allCountries.size(), allCountries);
    }

    @GetMapping("/{field}")
    public APIResponse<List<Country>> getCountriesWithSort(@PathVariable String field) {
        List<Country> allCountries = service.findCountriesWithSorting(field);
        return new APIResponse<>(allCountries.size(), allCountries);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public APIResponse<Page<Country>> getCountriesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Country> countriesWithPagination = service.findCountriesWithPagination(offset,pageSize);
        return new APIResponse<>(countriesWithPagination.getSize(), countriesWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Country>> getCountriesWithPaginationAndSort(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field) {
        Page<Country> countriesWithPaginationAndSorting = service.findCountriesWithPaginationAndSorting(offset,pageSize,field);
        return new APIResponse<>(countriesWithPaginationAndSorting.getSize(), countriesWithPaginationAndSorting);
    }
}
