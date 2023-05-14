package com.example.chartapp.service.pagandsort;

import com.example.chartapp.model.entity.Country;
import com.example.chartapp.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryPaginationAndSortingService {
    private final CountryRepository countryRepository;

    public List<Country> findAllCountries(){
        return countryRepository.findAll();
    }
    public List<Country> findCountriesWithSorting(String field) {
        return countryRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public Page<Country> findCountriesWithPagination(int offset, int pageSize) {
        Page<Country> countries = countryRepository.findAll(PageRequest.of(offset,pageSize));
        return countries;
    }

    public Page<Country> findCountriesWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Country> countries = countryRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return countries;
    }
}
