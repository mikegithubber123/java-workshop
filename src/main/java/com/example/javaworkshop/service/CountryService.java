package com.example.javaworkshop.service;

import java.util.List;
import com.example.javaworkshop.dao.CountryRepository;
import com.example.javaworkshop.dao.LanguageRepository;
import com.example.javaworkshop.dto.CountryDto;
import com.example.javaworkshop.exception.InvalidCountryCodeException;
import com.example.javaworkshop.model.Country;
import com.example.javaworkshop.model.CountryLanguage;
import com.example.javaworkshop.model.CountryLanguageId;
import com.example.javaworkshop.transform.CountryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class CountryService {
    private final CountryRepository countryRepository;
    private final LanguageRepository languageRepository;

    public CountryDto getCountryByCode(String code) {

        Country country = countryRepository.findById(code).orElseThrow(InvalidCountryCodeException::new);
        List<CountryLanguage> languages = languageRepository.findByCountryLanguageId_CountryCode(code);
        log.info("Found country: {}", country);

        CountryDto countryDto = CountryMapper.map(country);

        languages.stream()
                .findAny()
                .map(CountryLanguage::getCountryLanguageId)
                .map(CountryLanguageId::getLanguage)
                .ifPresent(countryDto::setCountryLanguage);

        return countryDto;
    }
}
