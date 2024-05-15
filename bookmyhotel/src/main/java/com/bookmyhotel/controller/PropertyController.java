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
            String messages = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining("\n"));
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
    public ResponseEntity<List<Property>> getAllProperties(@RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
                                                           @RequestParam(name = "pageSize",defaultValue = "5",required = false) int pageSize,
                                                           @RequestParam(name = "sortBy",defaultValue = "id",required = false) String sortBy,
                                                           @RequestParam(name = "sortDir",defaultValue = "asc",required = false) String sortDir)
    {
        List<Property> all = propertyService.getAll(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProperty(@PathVariable long id,@RequestBody PropertyDto propertyDto){
        PropertyDto dto = propertyService.updateProperty(id, propertyDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/getProperty/{propertyName}")
    public ResponseEntity<?> getPropertyByName(@PathVariable String propertyName){
        PropertyDto property = propertyService.getProperty(propertyName);
        return new ResponseEntity<>(property,HttpStatus.OK);
    }

    @GetMapping("findProperty/{id}")
    public ResponseEntity<?> getPropertyById(@PathVariable long id){
        PropertyDto dto = propertyService.getById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
