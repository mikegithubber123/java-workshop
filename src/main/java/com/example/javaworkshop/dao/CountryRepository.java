package com.example.javaworkshop.dao;

import com.example.javaworkshop.model.Country;
import org.springframework.data.repository.CrudRepository;


public interface CountryRepository extends CrudRepository<Country, String> {
}
