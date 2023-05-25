package com.example.chartapp.controller;

import com.example.chartapp.dto.CreateRegionDto;
import com.example.chartapp.entity.Region;
import com.example.chartapp.security.entity.dto.AuthRequest;
import com.example.chartapp.security.service.JwtService;
import com.example.chartapp.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/regions")
@RequiredArgsConstructor
public class RegionController {
    private final RegionService regionService;
    private final JwtService jwtService;
    private  AuthenticationManager authenticationManager;


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Region saveRegion(@RequestBody CreateRegionDto dto){
        return regionService.saveRegion(dto);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Region findRegionById(@PathVariable Long id){
        return regionService.findRegionById(id);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Region updateRegion(@PathVariable Long id, @RequestBody Region updatedRegion) {
        return regionService.updateRegion(id,updatedRegion);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
