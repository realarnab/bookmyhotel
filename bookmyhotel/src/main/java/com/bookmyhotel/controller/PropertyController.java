package com.bookmyhotel.controller;

import com.bookmyhotel.dto.PropertyDto;
import com.bookmyhotel.entity.Property;
import com.bookmyhotel.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/properties")
public class PropertyController {
    @Autowired
    private PropertyService propertyService;

    @GetMapping("/{locationName}") //to search the properties by location wise
    public ResponseEntity<List<Property>> findProperty(@PathVariable String locationName){
        //List<com.bookmyhotel.entity.Property> properties = propertyRepository.findPropertyByLocation(locationName);
        List<Property> allProperties = propertyService.findAllProperty(locationName);
        return new ResponseEntity<>(allProperties, HttpStatus.OK);
    }

    @PostMapping("/addProperty") //add new property
    public ResponseEntity<?> addNewCountry(@Valid @RequestBody PropertyDto dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String messages = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining("/n"));
            return new ResponseEntity<>(messages,HttpStatus.NOT_FOUND);
        }
        PropertyDto propertyDto = propertyService.addCountry(dto);
        return new ResponseEntity<>(propertyDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteProperty/{id}")
    public ResponseEntity<?> deleteProperty(@PathVariable long id){
        propertyService.removeProperty(id);
        return new ResponseEntity<>("Property deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Property>> getAllProperties(){
        List<Property> all = propertyService.getAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}
