package com.example.demo.controller;

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

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        Country country = countryService.getCountryById(id);
        return ResponseEntity.ok(country);
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable Long id, @RequestBody Country country) {
        Country updatedCountry = countryService.updateCountry(id, country);
        return ResponseEntity.ok(updatedCountry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{countryId}/cities/{cityId}")
    public ResponseEntity<Void> addCityToCountry(@PathVariable Long countryId, @PathVariable Long cityId) {
        countryService.addCityToCountry(countryId, cityId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{countryId}/city")
    public ResponseEntity<City> getCityForCountry(@PathVariable Long countryId) {
        City city = countryService.getCityForCountry(countryId);
        return ResponseEntity.ok(city);
    }

    @PutMapping("/{countryId}/city/{cityId}")
    public ResponseEntity<Void> updateCityForCountry(@PathVariable Long countryId, @PathVariable Long cityId) {
        countryService.updateCityForCountry(countryId, cityId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{countryId}/city")
    public ResponseEntity<Void> removeCityFromCountry(@PathVariable Long countryId) {
        countryService.removeCityFromCountry(countryId);
        return ResponseEntity.noContent().build();
    }

}
