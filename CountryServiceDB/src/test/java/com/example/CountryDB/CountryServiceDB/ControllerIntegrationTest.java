package com.example.CountryDB.CountryServiceDB;

import com.example.CountryDB.CountryServiceDB.beans.CountryDb;
import org.json.JSONException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class ControllerIntegrationTest {
    @Test @Order(1)
    void getAllCountriesIntegrationTes() throws JSONException {
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Germany\",\n" +
                "        \"capital\": \"Berlin\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"US\",\n" +
                "        \"capital\": \"Washington\"\n" +
                "    }\n" +
                "]";
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getCountries",String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }
    @Test @Order(2)
    void getCountryByIdIntegrationTest() throws JSONException {
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Germany\",\n" +
                "    \"capital\": \"Berlin\"\n" +
                "}";
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getCountries/1",String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }
    @Test @Order(3)
    void getCountryByNameIntegrationTest() throws JSONException {
        String expected = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Germany\",\n" +
                "    \"capital\": \"Berlin\"\n" +
                "}";
        TestRestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getCountries/countryName?name=Germany",String.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }
    @Test @Order(4)
    void addCountryIntegrationTest() throws JSONException {
        CountryDb country = new CountryDb(3,"Italy","Rome");
        String expected = "{\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"Italy\",\n" +
                "    \"capital\": \"Rome\"\n" +
                "}";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CountryDb> request = new HttpEntity<CountryDb>(country,headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/addCountry",request,String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }
    @Test @Order(5)
    void updateCountryIntegrationTest() throws JSONException {
        CountryDb country = new CountryDb(3,"UK","London");
        String expected = "{\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"UK\",\n" +
                "    \"capital\": \"London\"\n" +
                "}";
        TestRestTemplate restTemplate = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CountryDb> request = new HttpEntity<CountryDb>(country,headers);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/updateCountry/3",HttpMethod.PUT,request,String.class);
        System.out.println(response.getBody());
        assertEquals(HttpStatus.OK,response.getStatusCode());
        JSONAssert.assertEquals(expected,response.getBody(),false);
    }
    @Test @Order(6)
    void deleteCountryIntegrationTest() throws JSONException {
        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.delete("http://localhost:8080/deleteCountry/3");
    }

}
