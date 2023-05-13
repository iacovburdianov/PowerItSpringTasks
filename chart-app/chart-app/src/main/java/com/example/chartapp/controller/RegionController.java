package com.example.chartapp.controller;

import com.example.chartapp.model.entity.Region;
import com.example.chartapp.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/")
    public Region saveRegion(@RequestBody Region region){
        return regionService.saveRegion(region);
    }

    @GetMapping("/{id}")
    public Region findRegionById(@PathVariable Long id){
        return regionService.findRegionById(id);
    }

    @GetMapping("/")
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

}
