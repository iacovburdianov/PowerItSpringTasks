package com.example.chartapp.controller;

import com.example.chartapp.entity.Country;
import com.example.chartapp.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @PostMapping
    public Country saveCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @GetMapping("/{id}")
    public Country findCountryById(@PathVariable Long id){
        return countryService.findCountryById(id);
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }
    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Long id, @RequestBody Country updatedCountry) {
        return countryService.updateCountry(id,updatedCountry);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }


    //paging and sorting
    @GetMapping("/sorted/{field}")
    public List<Country> findCountriesWithSorting(String field) {
        return countryService.findCountriesWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Country> findCountriesWithPagination(int offset, int pageSize) {
        return countryService.findCountriesWithPagination(offset, pageSize);
    }

    @GetMapping("/sorted-paginated/{offset}/{pageSize}/{field}")
    public Page<Country> findCountriesWithPaginationAndSorting(int offset, int pageSize, String field) {
        return countryService.findCountriesWithPaginationAndSorting(offset, pageSize, field);
    }
}
