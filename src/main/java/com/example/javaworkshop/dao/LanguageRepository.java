package com.example.javaworkshop.dao;

import java.util.List;
import com.example.javaworkshop.model.CountryLanguageId;
import com.example.javaworkshop.model.CountryLanguage;
import org.springframework.data.repository.CrudRepository;


public interface LanguageRepository extends CrudRepository<CountryLanguage, CountryLanguageId> {
    List<CountryLanguage>findByCountryLanguageId_CountryCode(String countryCode);
}
