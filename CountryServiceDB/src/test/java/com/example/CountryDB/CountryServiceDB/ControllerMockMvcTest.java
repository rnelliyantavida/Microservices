package com.example.CountryDB.CountryServiceDB;

import com.example.CountryDB.CountryServiceDB.beans.AddResponse;
import com.example.CountryDB.CountryServiceDB.beans.CountryDb;
import com.example.CountryDB.CountryServiceDB.controller.ContryControllerDb;
import com.example.CountryDB.CountryServiceDB.services.ContryServiceDb;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ComponentScan(basePackages = "com.restservices.demo")
@ContextConfiguration
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ControllerMockMvcTest.class})
public class ControllerMockMvcTest {
    @Autowired
    MockMvc mockMvc;
    @Mock
    ContryServiceDb contryServiceDb;
    @InjectMocks
    ContryControllerDb contryControllerDb;
    List<CountryDb> countries;
    CountryDb country;

    @BeforeEach
    public void setUp(){
       mockMvc = MockMvcBuilders.standaloneSetup(contryControllerDb).build();
    }
    @Test @Order(1)
    public void test_getAllCountriers() throws Exception {
        countries = new ArrayList<>();
        countries.add(new CountryDb(1,"India","Delhi"));
        countries.add(new CountryDb(2,"US","Washington"));

        when(contryServiceDb.getAllCountries()).thenReturn(countries);
        this.mockMvc.perform(get("/getCountries"))
                .andDo(print());
    }
    @Test @Order(2)
    public void test_getCountryID() throws Exception {
        country = new CountryDb(1,"India","Delhi");
        int countryID = 1;
        when(contryServiceDb.getCountryByID(countryID)).thenReturn(country);
        this.mockMvc.perform(get("/getCountries/{id}",countryID))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".name").value("India"))
                .andExpect(MockMvcResultMatchers.jsonPath(".capital").value("Delhi"))
                .andDo(print());
    }
    @Test @Order(3)
    public void test_getByName() throws Exception {
        country = new CountryDb(1,"India","Delhi");
        String name = "India";
        when(contryServiceDb.getCountryByName(name)).thenReturn(country);
        this.mockMvc.perform(get("/getCountries/countryName").param("name",name))
                .andExpect(status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".name").value("India"))
                .andExpect(MockMvcResultMatchers.jsonPath(".capital").value("Delhi"))
                .andDo(print());
    }
    @Test @Order(4)
    public void test_addCountry() throws Exception {
        country = new CountryDb(3,"UK","London");
        when(contryServiceDb.addCountry(country)).thenReturn(country);
        ObjectMapper mapper = new ObjectMapper();
        String jsoncontent = mapper.writeValueAsString(country);
        this.mockMvc.perform(post("/addCountry")
                        .content(jsoncontent)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }
    @Test @Order(5)
    public void test_updateCountry() throws Exception {
        country = new CountryDb(1,"India","Delhi");
        int countryID = 1;
        when(contryServiceDb.getCountryByID(countryID)).thenReturn(country);
        when(contryServiceDb.updateCountry(country)).thenReturn(country);
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(country);
        this.mockMvc.perform(put("/updateCountry/{id}",countryID)
                                                  .content(jsonContent)
                                                  .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath(".id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath(".name").value("India"))
                .andExpect(MockMvcResultMatchers.jsonPath(".capital").value("Delhi"))
                .andDo(print());
    }
    @Test @Order(6)
    public void test_deleteCountry() throws Exception {
        AddResponse addResponse = new AddResponse(2,"Delete Successfully");
        int countryID = 2;
        when(contryServiceDb.deleteCountry(countryID)).thenReturn(addResponse);
        this.mockMvc.perform(delete("/deleteCountry/{id}",countryID))
                .andDo(print());
    }
}

