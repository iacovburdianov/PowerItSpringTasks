package com.example.chartapp.service;

import com.example.chartapp.model.dto.CreateRegionDto;
import com.example.chartapp.model.entity.Country;
import com.example.chartapp.model.entity.Region;
import com.example.chartapp.repository.CountryRepository;
import com.example.chartapp.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
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
        Country country = countryRepository.findById(dto.getCountryId()).orElseThrow(() -> new RuntimeException());
        Region region = new Region();
        region.setName(dto.getName());
        region.setCountry(country);
        return regionRepository.save(region);
    }

    //Read/Retrieve a region by ID:
    public Region findRegionById(Long id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        return optionalRegion.orElse(null);
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
        }
        return null;
    }

    //Delete a region:
    public void deleteRegion(Long id) {
        regionRepository.deleteById(id);
    }
}
