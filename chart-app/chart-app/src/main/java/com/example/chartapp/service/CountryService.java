package com.example.chartapp.service;

import com.example.chartapp.model.entity.Country;
import com.example.chartapp.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository repository;

    //Create/Save a new Country:
    public Country saveCountry(Country country) {
        return repository.save(country);
    }

    //Read/Retrieve a Country by ID:
    public Country findCountryById(Long id) {
        Optional<Country> optionalCountry = repository.findById(id);
        return optionalCountry.orElse(null);
    }

    //find all  countries
    public List<Country> getAllCountries() {
        return repository.findAll();
    }
    //Update an existing Country:
    public Country updateCountry(Long id, Country updatedCountry) {
        Optional<Country> optionalCountry = repository.findById(id);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            country.setName(updatedCountry.getName());
            country.setCode(updatedCountry.getCode());
            return repository.save(country);
        }
        return null;
    }


    //Delete a Country
    public void deleteCountry(Long id) {
        repository.deleteById(id);
    }
}
