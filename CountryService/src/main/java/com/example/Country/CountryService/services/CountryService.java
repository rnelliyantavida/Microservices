package com.example.Country.CountryService.services;

import com.example.Country.CountryService.beans.AddResponse;
import com.example.Country.CountryService.beans.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
public class CountryService {
    static HashMap<Integer, Country> countryHashMap;
    public CountryService(){
        countryHashMap = new HashMap<>();
        Country indiaCountry = new Country(1,"India","Capital");
        Country usCountry = new Country(2,"US","Washington");
        Country ukCountry = new Country(3,"UK","London");
        countryHashMap.put(1,indiaCountry);
        countryHashMap.put(2,usCountry);
        countryHashMap.put(3,ukCountry);
    }
    public List getAllCountries(){
        List countries = new ArrayList(countryHashMap.values());
        return countries;
    }
    public Country getCountryByID(int id){
        return countryHashMap.get(id);
    }
    public Country getCountryByName(String name){
        Country country = null;
        for(int i:countryHashMap.keySet()){
            if(countryHashMap.get(i).getName().equals(name))
                country = countryHashMap.get(i);
        }
        return country;
    }
    public Country addCountry(Country country){
        country.setId(getMaxId());
        countryHashMap.put(getMaxId(),country);
        return country;
    }
    public Country updateCountry(Country country){
        if(country.getId()>0)
            countryHashMap.put(country.getId(),country);
        return country;
    }
    public AddResponse deleteCountry(int id){
        countryHashMap.remove(id);
        return new AddResponse(id,"Deleted Successfully");
    }
    public static int getMaxId(){
        int max = 0;
        for(int id:countryHashMap.keySet()){
            if(max<+id)
                max = id;
        }
        return max+1;
    }
}
