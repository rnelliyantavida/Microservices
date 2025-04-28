package com.example.CountryDB.CountryServiceDB;

import com.example.CountryDB.CountryServiceDB.beans.AddResponse;
import com.example.CountryDB.CountryServiceDB.beans.CountryDb;
import com.example.CountryDB.CountryServiceDB.controller.ContryControllerDb;
import com.example.CountryDB.CountryServiceDB.services.ContryServiceDb;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {CountryControllerTest.class})
public class CountryControllerTest {
    @Mock
    ContryServiceDb contryServiceDb;

    @InjectMocks
    ContryControllerDb contryControllerDb;

    public List<CountryDb> countries;

    @Test @Order(1)
    public void test_getAllCountries(){
        countries = new ArrayList<>();
        countries.add(new CountryDb(1,"India","Delhi"));
        countries.add(new CountryDb(2,"US","Washington"));
        when(contryServiceDb.getAllCountries()).thenReturn(countries);
        assertEquals(2,contryControllerDb.getAllCountries().size());
    }

    @Test @Order(2)
    public void test_getByID(){
        CountryDb country = new CountryDb(2,"US","Washington");
        int countryId = 2;
        when(contryServiceDb.getCountryByID(countryId)).thenReturn(country);
        ResponseEntity<CountryDb> res = contryControllerDb.getByID(countryId);
        assertEquals(HttpStatus.FOUND,res.getStatusCode());
        assertEquals(countryId,res.getBody().getId());
    }

    @Test @Order(3)
    public void test_getByName(){
        CountryDb country = new CountryDb(2,"US","Washington");
        String name = "US";
        when(contryServiceDb.getCountryByName(name)).thenReturn(country);
        ResponseEntity<CountryDb> res = contryControllerDb.getByName(name);
        assertEquals(HttpStatus.FOUND,res.getStatusCode());
        assertEquals(name,res.getBody().getName());
    }

    @Test @Order(4)
    public void test_addCountry(){
        CountryDb country = new CountryDb(2,"US","Washington");
        when(contryServiceDb.addCountry(country)).thenReturn(country);
        assertEquals(country,contryControllerDb.addCountry(country));
    }

    @Test @Order(5)
    public void test_updateCountry(){
        CountryDb country = new CountryDb(2,"UK","London");
        int countryId = 2;
        when(contryServiceDb.getCountryByID(countryId)).thenReturn(country);
        when(contryServiceDb.updateCountry(country)).thenReturn(country);
        ResponseEntity<CountryDb> res = contryControllerDb.updateCountry(countryId,country);
        assertEquals(HttpStatus.OK,res.getStatusCode());
        assertEquals(country,res.getBody());
        assertEquals("UK",res.getBody().getName());
        assertEquals("London",res.getBody().getCapital());
    }

    @Test @Order(6)
    public void test_deleteCountry(){
        AddResponse addResponse = new AddResponse(1,"Delete Successfully");
        when(contryServiceDb.deleteCountry(addResponse.getId())).thenReturn(addResponse);
        AddResponse addResponse1 = contryControllerDb.deleteCountry(addResponse.getId());
        assertEquals(addResponse.getId(),addResponse1.getId());
    }
}
