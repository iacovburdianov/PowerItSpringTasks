package com.example.chartapp.service.pagandsort;

import com.example.chartapp.model.entity.City;
import com.example.chartapp.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityPaginationAndSortingService {
    private final CityRepository cityRepository;

    public List<City> findAllCities(){
        return cityRepository.findAll();
    }
    public List<City> findCitiesWithSorting(String field) {
        return cityRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public Page<City> findCitiesWithPagination(int offset, int pageSize) {
        Page<City> cities = cityRepository.findAll(PageRequest.of(offset,pageSize));
        return cities;
    }

    public Page<City> findCitiesWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<City> cities = cityRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return cities;
    }
}
