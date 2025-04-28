package com.example.CountryDB.CountryServiceDB.beans;

import com.fasterxml.jackson.databind.ObjectMapper;

public class AddResponse {
    int id;
    String message;

    public AddResponse(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

