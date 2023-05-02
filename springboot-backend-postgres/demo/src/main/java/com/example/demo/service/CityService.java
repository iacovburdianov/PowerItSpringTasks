package com.example.demo.service;

import com.example.demo.model.City;
import com.example.demo.repository.CityRepository;
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
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("City not found with id: " + id));
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City updateCity(Long id, City city) {
        City existingCity = cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("City not found with id:" + id));
        existingCity.setName(city.getName());
        existingCity.setPeopleCount(city.getPeopleCount());
        return cityRepository.save(existingCity);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }
}
