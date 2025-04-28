package com.example.Country.CountryService.controllers;

import com.example.Country.CountryService.beans.AddResponse;
import com.example.Country.CountryService.beans.Country;
import com.example.Country.CountryService.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    CountryService countryService;

    @GetMapping("/getCountries")
    public List getAllCountries(){
       return countryService.getAllCountries();
    }

    @GetMapping("/getCountries/{id}")
    public Country getByID(@PathVariable(value = "id") int id){
        return countryService.getCountryByID(id);
    }

    @GetMapping("/getCountries/countryName")
    public Country getByName(@RequestParam(value = "name") String name){
        return countryService.getCountryByName(name);
    }

    @PostMapping("/addCountry")
    public Country addCountry(@RequestBody Country country){
        return countryService.addCountry(country);
    }

    @PutMapping("/updateCountry")
    public Country updateCountry(@RequestBody Country country){
        return countryService.updateCountry(country);
    }

    @DeleteMapping("/deleteCountry/{id}")
    public AddResponse deleteCountry(@PathVariable(value = "id") int id){
        return countryService.deleteCountry(id);
    }
}
