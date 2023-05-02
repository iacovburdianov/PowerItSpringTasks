package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Admin on 5/2/2023
 *
 * @author : Admin
 * @date : 5/2/2023
 * @project : demo
 */
@Service
@Transactional
public class CountryService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public CountryService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }


    public Country getCountryById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public Country updateCountry(Long id, Country country) {
        Country existingCountry = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found with id: " + id));

        existingCountry.setName(country.getName());
        existingCountry.setCode(country.getCode());
        existingCountry.setCity(country.getCity());

        return countryRepository.save(existingCountry);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    //CRUD operations for city column
    // Create operation
    public void addCityToCountry(Long countryId, Long cityId) {
        // Retrieve the Country and City objects
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));
        City city = cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City not found with id " + cityId));

        // Associate the City with the Country
        country.setCity(city);

        // Save the changes to the Country
        countryRepository.save(country);
    }

    // Read operation
    public City getCityForCountry(Long countryId) {
        // Retrieve the Country object
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));

        // Return the associated City object
        return country.getCity();
    }

    // Update operation
    public void updateCityForCountry(Long countryId, Long cityId) {
        // Retrieve the Country and City objects
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));
        City city = cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException("City not found with id " + cityId));

        // Associate the City with the Country
        country.setCity(city);

        // Save the changes to the Country
        countryRepository.save(country);
    }

    // Delete operation
    public void removeCityFromCountry(Long countryId) {
        // Retrieve the Country object
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new EntityNotFoundException("Country not found with id " + countryId));

        // Remove the association with the City
        country.setCity(null);

        // Save the changes to the Country
        countryRepository.save(country);
    }
}
