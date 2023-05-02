package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City with id " + id + " not found"));
    }


    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(Long id, City updatedCity) {
        City city = getCityById(id);
        city.setName(updatedCity.getName());
        city.setPeopleCount(updatedCity.getPeopleCount());
        city.setCountry(updatedCity.getCountry());
        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}

