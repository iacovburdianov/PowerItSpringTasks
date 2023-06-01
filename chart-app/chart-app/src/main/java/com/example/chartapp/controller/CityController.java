package com.example.chartapp.controller;

import com.example.chartapp.dto.CreateCityDto;
import com.example.chartapp.entity.City;
import com.example.chartapp.security.entity.dto.AuthRequest;
import com.example.chartapp.security.service.JwtService;
import com.example.chartapp.service.CityService;
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
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public City saveCity(@RequestBody CreateCityDto dto) {
        return cityService.saveCity(dto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public City findCityById(@PathVariable("id") Long id){
        return cityService.findCityById(id);
    }


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public City updateCity(@PathVariable("id") Long id, @RequestBody City updatedCity) {
        return cityService.updateCity(id,updatedCity);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteCity(@PathVariable("id") Long id) {
        cityService.deleteCity(id);
    }

    @GetMapping("/sorted/{field}")
    public List<City> findCitiesWithSorting(@RequestParam String field) {
        return cityService.findCitiesWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<City> findCitiesWithPagination(@RequestParam int offset, @RequestParam int pageSize) {
        return cityService.findCitiesWithPagination(offset,pageSize);
    }

    @GetMapping("/sorted-paginated/{offset}/{pageSize}/{field}")
    public Page<City> findCitiesWithPaginationAndSorting(@RequestParam int offset, @RequestParam int pageSize, @RequestParam String field) {
        return cityService.findCitiesWithPaginationAndSorting(offset, pageSize, field);
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
