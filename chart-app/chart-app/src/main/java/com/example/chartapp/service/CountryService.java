package com.example.chartapp.service;

import com.example.chartapp.entity.Country;
import com.example.chartapp.repository.CountryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    //Create/Save a new Country:
    public Country saveCountry(Country country) {
        return countryRepository.save(country);
    }

    //Read/Retrieve a Country by ID:
    public Country findCountryById(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        return optionalCountry.orElseThrow(() -> new EntityNotFoundException("Country with id " + id + " not found"));
    }

    //find all  countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
    //Update an existing Country:
    public Country updateCountry(Long id, Country updatedCountry) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            country.setName(updatedCountry.getName());
            country.setCode(updatedCountry.getCode());
            return countryRepository.save(country);
        }else {
            throw new EntityNotFoundException("Country with id " + id + " not found");
        }
    }


    //Delete a Country
    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

    //paging and sorting
    public List<Country> findCountriesWithSorting(String field) {
        return countryRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public Page<Country> findCountriesWithPagination(int offset, int pageSize) {
        return countryRepository.findAll(PageRequest.of(offset,pageSize));
    }

    public Page<Country> findCountriesWithPaginationAndSorting(int offset, int pageSize, String field) {
        return countryRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }
}
