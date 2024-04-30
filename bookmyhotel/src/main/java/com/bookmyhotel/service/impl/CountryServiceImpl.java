package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.CountryDto;
import com.bookmyhotel.entity.Country;
import com.bookmyhotel.exceptions.CountryNotFoundException;
import com.bookmyhotel.repository.CountryRepository;
import com.bookmyhotel.service.CountryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ModelMapper modelMapper;

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

    @Override
    public List<CountryDto> getAllCountries() {
        List<Country> all = countryRepository.findAll();
        List<CountryDto> countries = all.stream().map((element) -> modelMapper.map(element, CountryDto.class)).collect(Collectors.toList());
        return countries;
    }
}

