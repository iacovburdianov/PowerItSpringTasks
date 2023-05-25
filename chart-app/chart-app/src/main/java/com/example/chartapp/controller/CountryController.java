package com.example.chartapp.controller;

import com.example.chartapp.entity.Country;
import com.example.chartapp.security.entity.dto.AuthRequest;
import com.example.chartapp.security.service.JwtService;
import com.example.chartapp.service.CountryService;
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
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    private final JwtService jwtService;
    private  AuthenticationManager authenticationManager;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Country saveCountry(@RequestBody Country country) {
        return countryService.saveCountry(country);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Country findCountryById(@PathVariable Long id){
        return countryService.findCountryById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<Country> getAllCountries() {
        return countryService.getAllCountries();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Country updateCountry(@PathVariable Long id, @RequestBody Country updatedCountry) {
        return countryService.updateCountry(id,updatedCountry);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
    }


    //paging and sorting
    @GetMapping("/sorted/{field}")
    public List<Country> findCountriesWithSorting(String field) {
        return countryService.findCountriesWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Country> findCountriesWithPagination(int offset, int pageSize) {
        return countryService.findCountriesWithPagination(offset, pageSize);
    }

    @GetMapping("/sorted-paginated/{offset}/{pageSize}/{field}")
    public Page<Country> findCountriesWithPaginationAndSorting(int offset, int pageSize, String field) {
        return countryService.findCountriesWithPaginationAndSorting(offset, pageSize, field);
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
