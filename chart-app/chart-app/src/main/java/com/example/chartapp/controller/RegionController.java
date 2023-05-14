package com.example.chartapp.controller;

import com.example.chartapp.dto.CreateRegionDto;
import com.example.chartapp.entity.Region;
import com.example.chartapp.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping
    public Region saveRegion(@RequestBody CreateRegionDto dto){
        return regionService.saveRegion(dto);
    }

    @GetMapping("/{id}")
    public Region findRegionById(@PathVariable Long id){
        return regionService.findRegionById(id);
    }

    @GetMapping
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }
    @PutMapping("/{id}")
    public Region updateRegion(@PathVariable Long id, @RequestBody Region updatedRegion) {
        return regionService.updateRegion(id,updatedRegion);
    }

    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable Long id) {
        regionService.deleteRegion(id);
    }


    //paging and sorting
    @GetMapping("/sorted/{field}")
    public List<Region> findRegionsWithSorting(String field) {
        return regionService.findRegionsWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Region> findRegionsWithPagination(int offset, int pageSize) {
        return regionService.findRegionsWithPagination(offset, pageSize);
    }

    @GetMapping("/sorted-paginated/{offset}/{pageSize}/{field}")
    public Page<Region> findRegionsWithPaginationAndSorting(int offset, int pageSize, String field) {
        return regionService.findRegionsWithPaginationAndSorting(offset, pageSize, field);
    }
}
