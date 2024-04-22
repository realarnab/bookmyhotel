package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.CountryDto;
import com.bookmyhotel.entity.Country;
import com.bookmyhotel.exceptions.CountryNotFoundException;
import com.bookmyhotel.repository.CountryRepository;
import com.bookmyhotel.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Override
    public void addNewCountry(CountryDto countryDto) {
        Country country=new Country();
        country.setId(countryDto.getId());
        country.setCountryName(countryDto.getCountryName());
        countryRepository.save(country);
    }

    @Override
    public void deleteCountry(long id) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country name not found with id: " + id));
        if (country!=null){
            countryRepository.deleteById(id);
        }
        }
    }

