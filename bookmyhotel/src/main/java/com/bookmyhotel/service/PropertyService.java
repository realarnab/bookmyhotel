package com.bookmyhotel.service;

import com.bookmyhotel.dto.PropertyDto;
import com.bookmyhotel.entity.Property;

import java.util.List;

public interface PropertyService {
    public List<Property> findAllProperty(String locationName);

    PropertyDto addCountry(PropertyDto dto);

    void removeProperty(long id);

    List<Property> getAll();

    PropertyDto updateProperty(long id, PropertyDto propertyDto);

    PropertyDto getProperty(String propertyName);

    PropertyDto getById(long id);
}
