package com.example.CountryDB.CountryServiceDB;

import com.example.CountryDB.CountryServiceDB.beans.AddResponse;
import com.example.CountryDB.CountryServiceDB.beans.CountryDb;
import com.example.CountryDB.CountryServiceDB.repositories.CountryRepository;
import com.example.CountryDB.CountryServiceDB.services.ContryServiceDb;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {CountryServiceDbApplicationTests.class})
class CountryServiceDbApplicationTests {
	@Mock
	CountryRepository countryRepository;

	@InjectMocks
	ContryServiceDb contryServiceDb;

	public List<CountryDb> countries;

	@Test
	@Order(1)
	public void test_getAllCountries() {
		List<CountryDb> countries = new ArrayList<>();
		countries.add(new CountryDb(1,"India","Capital"));
		countries.add(new CountryDb(2,"UK","London"));

		when(countryRepository.findAll()).thenReturn(countries);
		assertEquals(2,contryServiceDb.getAllCountries().size());
	}

	@Test @Order(2)
	public void test_getCountryByID(){
		List<CountryDb> countries = new ArrayList<>();
		countries.add(new CountryDb(1,"India","Capital"));
		countries.add(new CountryDb(2,"UK","London"));
		int countryId=2;
		when(countryRepository.findAll()).thenReturn(countries);
		assertEquals(countryId,contryServiceDb.getCountryByID(countryId).getId());
	}

	@Test @Order(3)
	public void test_getCountryByName(){
		List<CountryDb> countries = new ArrayList<>();
		countries.add(new CountryDb(1,"India","Capital"));
		countries.add(new CountryDb(2,"UK","London"));
		String name = "India";
		when(countryRepository.findAll()).thenReturn(countries);
		assertEquals(name,contryServiceDb.getCountryByName(name).getName());
	}
	@Test @Order(4)
	public void test_addCountry(){
		CountryDb country = new CountryDb(3,"US","Washington");
		when(countryRepository.save(country)).thenReturn(country);
		assertEquals(country,contryServiceDb.addCountry(country));
	}

	@Test @Order(5)
	public void test_updateCountry(){
		CountryDb country = new CountryDb(3,"US","Washington");
		when(countryRepository.save(country)).thenReturn(country);
		assertEquals(country,contryServiceDb.addCountry(country));
	}

	@Test @Order(6)
	public void test_deleteCountry(){
		CountryDb country = new CountryDb(3,"US","Washington");
		contryServiceDb.deleteCountry(country.getId());
		verify(countryRepository,times(1));
	}
}
