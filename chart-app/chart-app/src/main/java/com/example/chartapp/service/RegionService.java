package com.example.chartapp.service;

import com.example.chartapp.model.entity.Region;
import com.example.chartapp.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepository repository;

    //Create/Save a new Region:
    public Region saveRegion(Region region) {
        return repository.save(region);
    }

    //Read/Retrieve a region by ID:
    public Region findRegionById(Long id) {
        Optional<Region> optionalRegion = repository.findById(id);
        return optionalRegion.orElse(null);
    }

    //find all  regions
    public List<Region> getAllRegions() {
        return repository.findAll();
    }

    //Update an existing region:
    public Region updateRegion(Long id, Region updateRegion) {
        Optional<Region> optionalRegion = repository.findById(id);
        if (optionalRegion.isPresent()) {
            Region region = optionalRegion.get();
            region.setName(updateRegion.getName());
            region.setCountry(updateRegion.getCountry());
            return repository.save(region);
        }
        return null;
    }

    //Delete a region:
    public void deleteRegion(Long id) {
        repository.deleteById(id);
    }
}
