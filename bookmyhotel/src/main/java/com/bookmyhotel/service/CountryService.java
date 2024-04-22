package com.bookmyhotel.service;

import com.bookmyhotel.dto.CountryDto;
import com.bookmyhotel.entity.Country;

public interface CountryService {
    public void addNewCountry(CountryDto countryDto);
    public void deleteCountry(long id);
}
