package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.PropertyDto;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.exceptions.PropertyNotFound;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Property> findAllProperty(String locationName) {
        List<Property> properties = propertyRepository.findPropertyByLocation(locationName);
        return properties;
    }

    @Override
    public PropertyDto addCountry(PropertyDto dto) {
        Property property = mapToEntity(dto);
        Property save = propertyRepository.save(property);
        PropertyDto propertyDto = mapToDto(property);
        return propertyDto;
    }

    @Override
    public void removeProperty(long id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFound("Property not found with id: " + id));
        if (property!=null){
            propertyRepository.deleteById(id);
        }
    }

    @Override
    public List<Property> getAll() {
        List<Property> all = propertyRepository.findAll();
        return all;
    }

    public Property mapToEntity(PropertyDto dto){
        return modelMapper.map(dto,Property.class);
    }

    public PropertyDto mapToDto(Property property){
        return modelMapper.map(property,PropertyDto.class);
    }
}
