package com.example.CountryDB.CountryServiceDB.controller;

import com.example.CountryDB.CountryServiceDB.beans.AddResponse;
import com.example.CountryDB.CountryServiceDB.beans.CountryDb;
import com.example.CountryDB.CountryServiceDB.services.ContryServiceDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class ContryControllerDb {
    @Autowired
    ContryServiceDb contryServiceDb;

    @GetMapping("/getCountries")
    public List<CountryDb> getAllCountries(){
        return contryServiceDb.getAllCountries();
    }

    @GetMapping("/getCountries/{id}")
    public ResponseEntity<CountryDb> getByID(@PathVariable(value = "id") int id){
        try{
            CountryDb country = contryServiceDb.getCountryByID(id);
            return new ResponseEntity<CountryDb>(country, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getCountries/countryName")
    public ResponseEntity<CountryDb> getByName(@RequestParam(value = "name") String name){
        try{
            CountryDb country = contryServiceDb.getCountryByName(name);
            return new ResponseEntity<CountryDb>(country, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addCountry")
    public CountryDb addCountry(@RequestBody CountryDb country){
        return contryServiceDb.addCountry(country);
    }

    @PutMapping("/updateCountry/{id}")
    public ResponseEntity<CountryDb> updateCountry(@PathVariable(value = "id") int id, @RequestBody CountryDb country){
        try{
            CountryDb existCountry = contryServiceDb.getCountryByID(id);
            existCountry.setName(country.getName());
            existCountry.setCapital(country.getCapital());
            CountryDb updatedCountry = contryServiceDb.updateCountry(existCountry);
            return new ResponseEntity<CountryDb>(updatedCountry,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/deleteCountry/{id}")
    public AddResponse deleteCountry(@PathVariable(value = "id") int id){
        return contryServiceDb.deleteCountry(id);
    }
}
