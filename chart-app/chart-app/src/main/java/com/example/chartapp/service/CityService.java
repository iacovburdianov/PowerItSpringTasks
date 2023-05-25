package com.example.chartapp.service;

import com.example.chartapp.dto.CreateCityDto;
import com.example.chartapp.entity.City;
import com.example.chartapp.entity.Region;
import com.example.chartapp.repository.CityRepository;
import com.example.chartapp.repository.RegionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CityService {
    private final CityRepository cityRepository;
    private final RegionRepository regionRepository;

    public CityService(CityRepository cityRepository, RegionRepository regionRepository) {
        this.cityRepository = cityRepository;
        this.regionRepository = regionRepository;
    }

    //Create/Save a new City:
    public City saveCity(CreateCityDto dto) {
        Region region = regionRepository.findById(dto.getRegionId()).orElseThrow(RuntimeException::new);
        City city = new City();
        city.setName(dto.getName());
        city.setRegion(region);
        city.setPeopleCount(dto.getPeopleCount());
        return cityRepository.save(city);
    }


    //Read/Retrieve a City by id:
    public City findCityById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        return optionalCity.orElseThrow(() -> new EntityNotFoundException("City with id " + id + " not found"));
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    //Update an existing Region:
    public City updateCity(Long id, City updatedCity) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()){
            City city = optionalCity.get();
            city.setName(updatedCity.getName());
            city.setPeopleCount(updatedCity.getPeopleCount());
            city.setRegion(updatedCity.getRegion());
            return cityRepository.save(city);
        }else {
            throw new EntityNotFoundException("City with id " + id + " not found");
        }
    }

    //Delete a city:
    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }




    //pagination and sorting

    public List<City> findCitiesWithSorting(String field) {
        return cityRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public Page<City> findCitiesWithPagination(int offset, int pageSize) {
        return cityRepository.findAll(PageRequest.of(offset,pageSize));
    }

    public Page<City> findCitiesWithPaginationAndSorting(int offset, int pageSize, String field) {
        return cityRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }
}
