package com.example.CountryDB.CountryServiceDB.services;

import com.example.CountryDB.CountryServiceDB.beans.AddResponse;
import com.example.CountryDB.CountryServiceDB.beans.CountryDb;
import com.example.CountryDB.CountryServiceDB.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ContryServiceDb {
    @Autowired
    CountryRepository countryRepository;
    public List getAllCountries() {
        return countryRepository.findAll();
    }
    public CountryDb getCountryByID(int id){
        CountryDb country = null;
        List<CountryDb> countries = countryRepository.findAll();
        for(CountryDb countryDb:countries){
            if(countryDb.getId() == id)
                country = countryDb;
        }
        return country;
    }
    public CountryDb getCountryByName(String name){
        CountryDb country = null;
        List<CountryDb> countries = countryRepository.findAll();
        for(CountryDb countryDb:countries){
            if(countryDb.getName().equals(name)){
                country = countryDb;
            }
        }
        return country;
    }
    public CountryDb addCountry(CountryDb country){
        country.setId(getMaxId());
        return countryRepository.save(country);
    }
    public CountryDb updateCountry(CountryDb country){
        return countryRepository.save(country);
    }
    public AddResponse deleteCountry(int id){
        CountryDb country = getCountryByID(id);
        countryRepository.delete(country);
        return new AddResponse(id,"Delete successfully");
    }
    public int getMaxId(){
        return countryRepository.findAll().size()+1;
    }

}
