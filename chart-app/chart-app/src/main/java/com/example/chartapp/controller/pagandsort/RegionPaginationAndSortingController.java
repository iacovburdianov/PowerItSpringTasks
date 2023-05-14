package com.example.chartapp.controller.pagandsort;

import com.example.chartapp.model.dto.APIResponse;
import com.example.chartapp.model.entity.Region;
import com.example.chartapp.service.pagandsort.RegionPaginationAndSortingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("regionPagAndSort")
@RequiredArgsConstructor
public class RegionPaginationAndSortingController {
    private final RegionPaginationAndSortingService service;

    @GetMapping
    public APIResponse<List<Region>> getRegions() {
        List<Region> allRegions = service.findAllRegions();
        return new APIResponse<>(allRegions.size(), allRegions);
    }

    @GetMapping("/{field}")
    public APIResponse<List<Region>> getRegionsWithSort(@PathVariable String field) {
        List<Region> allRegions = service.findRegionsWithSorting(field);
        return new APIResponse<>(allRegions.size(), allRegions);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public APIResponse<Page<Region>> getRegionsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Region> regionsWithPagination = service.findRegionsWithPagination(offset,pageSize);
        return new APIResponse<>(regionsWithPagination.getSize(), regionsWithPagination);
    }

    @GetMapping("/paginationAndSort/{offset}/{pageSize}/{field}")
    public APIResponse<Page<Region>> getCountriesWithPaginationAndSort(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field) {
        Page<Region> regionsWithPaginationAndSorting = service.findRegionsWithPaginationAndSorting(offset,pageSize,field);
        return new APIResponse<>(regionsWithPaginationAndSorting.getSize(), regionsWithPaginationAndSorting);
    }
}
