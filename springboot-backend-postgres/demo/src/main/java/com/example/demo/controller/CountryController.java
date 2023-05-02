package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Admin on 5/2/2023
 *
 * @author : Admin
 * @date : 5/2/2023
 * @project : demo
 */
@RestController
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Long id) {
        return countryService.getCountryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Country createCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@PathVariable Long id, @RequestBody Country country) {
        Country existingCountry = countryService.getCountryById(id);

        if (existingCountry == null) {
            throw new ResourceNotFoundException("Country not found with id: " + id);
        }

        existingCountry.setName(country.getName());
        existingCountry.setCode(country.getCode());
        existingCountry.setCities(country.getCities());

        return countryService.saveCountry(existingCountry);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCountryById(@PathVariable Long id) {
        countryService.deleteCountryById(id);
    }
}

