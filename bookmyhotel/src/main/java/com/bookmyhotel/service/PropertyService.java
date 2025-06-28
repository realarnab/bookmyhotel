package com.bookmyhotel.service;

import com.bookmyhotel.dto.PropertyDto;
import com.bookmyhotel.entity.Property;

import java.util.List;

public interface PropertyService {
    public List<Property> findAllProperty(String locationName);

    PropertyDto addProperty(PropertyDto dto);

    void removeProperty(long id);

    List<Property> getAll(int pageNo,int pageSize,String sortBy,String sortDir);

    PropertyDto updateProperty(long id, PropertyDto propertyDto);

    PropertyDto getProperty(String propertyName);

    PropertyDto getById(long id);
}
