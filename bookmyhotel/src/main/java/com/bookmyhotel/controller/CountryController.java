package com.bookmyhotel.controller;

import com.bookmyhotel.dto.CountryDto;
import com.bookmyhotel.repository.CountryRepository;
import com.bookmyhotel.service.CountryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @PostMapping("/addCountry")
    public ResponseEntity<?> addCountry(@Valid @RequestBody CountryDto countryDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String collect = bindingResult.getAllErrors().stream().map(e ->e.getDefaultMessage()).collect(Collectors.joining("/n"));
            return new ResponseEntity<>(collect,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        countryService.addNewCountry(countryDto);
        return new ResponseEntity<>("Country Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteCountry/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable long id){
        countryService.deleteCountry(id);
        return new ResponseEntity<>("Country deleted successfully",HttpStatus.OK);
    }
}
