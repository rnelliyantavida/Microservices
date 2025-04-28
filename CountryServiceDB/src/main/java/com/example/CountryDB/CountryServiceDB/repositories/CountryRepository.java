package com.example.CountryDB.CountryServiceDB.repositories;

import com.example.CountryDB.CountryServiceDB.beans.CountryDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<CountryDb,Integer> {
}
