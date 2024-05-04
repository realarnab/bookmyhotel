package com.bookmyhotel.service;

import com.bookmyhotel.dto.CountryDto;
import com.bookmyhotel.entity.Country;

import java.util.List;

public interface CountryService {
    public void addNewCountry(CountryDto countryDto);
    public void deleteCountry(long id);

    List<CountryDto> getAllCountries();

    CountryDto updateACountry(long id, CountryDto countryDto);
}
