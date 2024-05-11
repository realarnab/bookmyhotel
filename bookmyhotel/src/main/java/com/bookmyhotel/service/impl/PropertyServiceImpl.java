package com.bookmyhotel.service.impl;

import com.bookmyhotel.dto.PropertyDto;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.exceptions.PropertyNotFound;
import com.bookmyhotel.repository.PropertyRepository;
import com.bookmyhotel.service.PropertyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Property> getAll(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending(); //used ternary operator to check that whether the condition is ASC or DESC
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Property> pages = propertyRepository.findAll(pageable);
        List<Property> all = pages.getContent();
        return all;
    }

    @Override
    public PropertyDto updateProperty(long id, PropertyDto propertyDto) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFound("Property not found with id:  " + id));
        Property prop = mapToEntity(propertyDto);
        prop.setId(id);
        Property saved = propertyRepository.save(prop);
        PropertyDto dto = mapToDto(saved);
        return dto;
    }

    @Override
    public PropertyDto getProperty(String propertyName) {
        Property property = propertyRepository.findByPropertyName(propertyName).orElseThrow(()->new PropertyNotFound("No Property found with name: "+propertyName));
        return mapToDto(property);
    }

    @Override
    public PropertyDto getById(long id) {
        Property property = propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFound("No Property found with id: " + id));
        return mapToDto(property);
    }

    public Property mapToEntity(PropertyDto dto){
        return modelMapper.map(dto,Property.class);
    }

    public PropertyDto mapToDto(Property property){
        return modelMapper.map(property,PropertyDto.class);
    }
}
