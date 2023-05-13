package com.example.chartapp.service;

import com.example.chartapp.model.entity.City;
import com.example.chartapp.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository repository;

    //Create/Save a new City:
    public City saveCity(City city) {
        return repository.save(city);
    }

    //Read/Retrieve a City by id:
    public City findCityById(Long id) {
        Optional<City> optionalCity = repository.findById(id);
        return optionalCity.orElse(null);
    }

    public List<City> getAllCities() {
        return repository.findAll();
    }

    //Update an existing Region:
    public City updateCity(Long id, City updatedCity) {
        Optional<City> optionalCity = repository.findById(id);
        if (optionalCity.isPresent()){
            City city = optionalCity.get();
            city.setName(updatedCity.getName());
            city.setPeopleCount(updatedCity.getPeopleCount());
            city.setRegion(updatedCity.getRegion());
            return repository.save(city);
        }
        return null;
    }

    //Delete a city:
    public void deleteCity(Long id) {
        repository.deleteById(id);
    }
}
