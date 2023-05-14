package com.example.chartapp.service;

import com.example.chartapp.dto.CreateRegionDto;
import com.example.chartapp.entity.Country;
import com.example.chartapp.entity.Region;
import com.example.chartapp.repository.CountryRepository;
import com.example.chartapp.repository.RegionRepository;
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
public class RegionService {
    private final RegionRepository regionRepository;
    private final CountryRepository countryRepository;

    //Create/Save a new Region:
    public Region saveRegion(CreateRegionDto dto) {
        Country country = countryRepository.findById(dto.getCountryId()).orElseThrow(RuntimeException::new);
        Region region = new Region();
        region.setName(dto.getName());
        region.setCountry(country);
        return regionRepository.save(region);
    }

    //Read/Retrieve a region by ID:
    public Region findRegionById(Long id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        return optionalRegion.orElseThrow(() -> new EntityNotFoundException("Region with id " + id + " not found"));
    }

    //find all  regions
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    //Update an existing region:
    public Region updateRegion(Long id, Region updateRegion) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isPresent()) {
            Region region = optionalRegion.get();
            region.setName(updateRegion.getName());
            region.setCountry(updateRegion.getCountry());
            return regionRepository.save(region);
        }else {
            throw new EntityNotFoundException("Region with id " + id + " not found");
        }
    }

    //Delete a region:
    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }



    //paging and sorting
    public List<Region> findRegionsWithSorting(String field) {
        return regionRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public Page<Region> findRegionsWithPagination(int offset, int pageSize) {
        return regionRepository.findAll(PageRequest.of(offset,pageSize));
    }

    public Page<Region> findRegionsWithPaginationAndSorting(int offset, int pageSize, String field) {
        return regionRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
    }
}
