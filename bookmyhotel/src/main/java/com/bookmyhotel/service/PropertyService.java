package com.bookmyhotel.service;

import com.bookmyhotel.dto.PropertyDto;
import com.bookmyhotel.entity.Property;

import java.util.List;

public interface PropertyService {
    public List<Property> findAllProperty(String locationName);

    PropertyDto addCountry(PropertyDto dto);

    void removeProperty(long id);

    List<Property> getAll();
}
