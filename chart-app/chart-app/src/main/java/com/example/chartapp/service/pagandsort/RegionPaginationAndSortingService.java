package com.example.chartapp.service.pagandsort;

import com.example.chartapp.model.entity.Country;
import com.example.chartapp.model.entity.Region;
import com.example.chartapp.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionPaginationAndSortingService {
    private final RegionRepository regionRepository;

    public List<Region> findAllRegions(){
        return regionRepository.findAll();
    }
    public List<Region> findRegionsWithSorting(String field) {
        return regionRepository.findAll(Sort.by(Sort.Direction.DESC,field));
    }

    public Page<Region> findRegionsWithPagination(int offset, int pageSize) {
        Page<Region> regions = regionRepository.findAll(PageRequest.of(offset,pageSize));
        return regions;
    }

    public Page<Region> findRegionsWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Region> regions = regionRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        return regions;
    }
}
