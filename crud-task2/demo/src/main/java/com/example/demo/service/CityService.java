package com.example.demo.service;

import com.example.demo.repository.City;
import com.example.demo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Admin on 5/1/2023
 *
 * @author : Admin
 * @date : 5/1/2023
 * @project : demo
 */
@Service
public class CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // Create operation
    public City createCity(City city) {
        return cityRepository.save(city);
    }

    // Read operation
    public City getCityById(Long id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {
            return city.get();
        } else {
            throw new RuntimeException("City not found");
        }
    }

    // Update operation
    public City updateCity(Long id, City city) {
        Optional<City> existingCity = cityRepository.findById(id);
        if (existingCity.isPresent()) {
            City updatedCity = existingCity.get();
            updatedCity.setName(city.getName());
            updatedCity.setPeopleCount(city.getPeopleCount());
            updatedCity.setCountry(city.getCountry());
            return cityRepository.save(updatedCity);
        } else {
            throw new RuntimeException("City not found");
        }
    }

    // Delete operation
    public void deleteCity(Long id) {
        Optional<City> city = cityRepository.findById(id);
        if (city.isPresent()) {
            cityRepository.delete(city.get());
        } else {
            throw new RuntimeException("City not found");
        }
    }

}

