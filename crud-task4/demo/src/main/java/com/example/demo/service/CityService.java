package com.example.demo.service;

import com.example.demo.entity.City;
import com.example.demo.entity.Country;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 5/2/2023
 *
 * @author : Admin
 * @date : 5/2/2023
 * @project : demo
 */
@Service
public class CityService {


    private final CityRepository cityRepository;
    
    private final CountryRepository countryRepository;

    public CityService(CityRepository cityRepository, CountryRepository countryRepository) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public List<City> getAllCities() {
        return (List<City>) cityRepository.findAll();
    }

    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    public void deleteCityById(Long id) {
        cityRepository.deleteById(id);
    }

    public City createCity(City city) {
        Optional<Country> optionalCountry = countryRepository.findById(city.getCountry().getId());
        if (!optionalCountry.isPresent()) {
            throw new IllegalArgumentException("Invalid country ID: " + city.getCountry().getId());
        }
        city.setCountry(optionalCountry.get());
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City city) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (!optionalCity.isPresent()) {
            throw new IllegalArgumentException("Invalid city ID: " + id);
        }
        City existingCity = optionalCity.get();
        existingCity.setName(city.getName());
        existingCity.setPeopleCount(city.getPeopleCount());

        Optional<Country> optionalCountry = countryRepository.findById(city.getCountry().getId());
        if (!optionalCountry.isPresent()) {
            throw new IllegalArgumentException("Invalid country ID: " + city.getCountry().getId());
        }
        existingCity.setCountry(optionalCountry.get());

        return cityRepository.save(existingCity);
    }

    public boolean deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            return false;
        }
        cityRepository.deleteById(id);
        return true;
    }
}
